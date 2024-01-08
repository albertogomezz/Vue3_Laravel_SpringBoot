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
    public function update(Request $request, $id)
    {
        $data = $request->all();

        $reservation = Reservation::findOrFail($id);
        $pista = Pista::where('id', $reservation->pista_id)->firstOrFail();
        $user = User::where('id',  $reservation->user_id)->firstOrFail();

        if (isset($data["state"]) && in_array($data["state"], [0, 1, 2])) {
            $reservation->state = $data["state"];
        }

        if (isset($data["pista_id"])) {
            $reservation->pista_id = $data["pista_id"];
        }
        if (isset($data["user_id"])) {
            $reservation->user_id = $data["user_id"];
        }


        if (isset($data["date"])) {
            $reservationDate = new \DateTime($data['date']);
            $now = new \DateTime();

            if ($now > $reservationDate) {
                return response()->json([
                    "Error" => "The reservation date cannot be in the past."
                ], 404);
            }

            $reservation->date = $data["date"];
        }

        $reservation->save();

        return response()->json([
            "Message" => "Reservation updated successfully."
        ], 200);
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
