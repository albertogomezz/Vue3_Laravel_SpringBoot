<?php

namespace App\Http\Controllers;

use App\Http\Requests\Reservation\UpdateReservationRequest;
use App\Http\Resources\ReservationResource;
use App\Models\Pista;
use App\Models\Reservation;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Ramsey\Uuid\Type\Integer;

class ReservationController extends Controller
{
    public function index()
    {
        return ReservationResource::collection(Reservation::all());
    }

    public function show($id)
    {
        return ReservationResource::make(Reservation::where('id', $id)->firstOrFail());
    }

    public function update(Request $request, $id){
        $data = $request->all();

        $reservation = Reservation::findOrFail($id);

        if (isset($data["user_id"])) {
            $user = User::find($data["user_id"]);

            if ($user) {
                $reservation->user_id = $data["user_id"];
            } else {
                return response()->json([
                    "Error" => "The provided user_id does not correspond to an existing user."
                ]);
            }
        }

        if (isset($data["pista_id"])) {
            $pista = Pista::find($data["pista_id"]);

            if ($pista) {
                $reservation->pista_id = $data["pista_id"];
            } else {
                return response()->json([
                    "Error" => "The provided pista_id does not correspond to an existing pista."
                ]);
            }
        }

        $reservation->save();

        return response()->json([
            "Message" => "Reservation updated successfully."
        ]);
    }

    public function destroy($id)
    {
        $delete = Reservation::where('id', $id)->delete();

        if ($delete == 1) {
            return response()->json([
                "Message" => "Deleted correctly"
            ], 200);
        } else {
            return response()->json([
                "Status" => "Not found"
            ], 404);
        }
    }
}
