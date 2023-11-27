<?php

namespace App\Http\Controllers;
use Illuminate\Http\Request;
use App\Models\Pista;
use App\Models\Sport;

//RESOURCE
use App\Http\Resources\PistaResource;

//REQUEST
use App\Http\Requests\Pista\StorePistaRequest;
use App\Http\Requests\Pista\UpdatePistaRequest;

class PistaController extends Controller
{   

    public function store(StorePistaRequest $request) {
        $data = $request->except(['sports']);

        $sports = Sport::whereIn('sport_name', $request->sports)->get();

        $sports_id = [];
        foreach ($sports as $c) {
            array_push($sports_id, $c->id);
        }

        if (count($sports_id) > 0) {
            $pista = Pista::create($data);
            $pista->sports()->sync($sports_id);
            return PistaResource::make($pista);
        } else {
            return response()->json([
                "Status" => "Not found"
            ], 404);
        }
    }

    public function index() {   
        return PistaResource::collection(Pista::all());
    }

    public function show($id) {

        if(Pista::where('id', $id)->exists()) {
            return PistaResource::make(Pista::where('id', $id)->firstOrFail());
        } else {
            return response()->json([
                "message" => "Pista not found"
            ], 404);
        }
    }

    public function update(UpdatePistaRequest $request, $id)
    {   
        $data = $request->except(['sports']);
        $sports_name = [];
        if ($request->sports !== null) {
            $sports_name = $request->sports;
        }
        $sports = Sport::whereIn('sport_name', $sports_name)->get();
        $sports_id = [];
        foreach ($sports as $c) {
            array_push($sports_id, $c->id);
        }

        $update = Pista::where('id', $id)->update($data);

        if ($update == 1) {
            if (count($sports_id) > 0) {
                $pista = Pista::where('id', $id)->firstOrFail();
                $pista->sports()->detach();
                $pista->sports()->sync($sports_id);
            }

            return response()->json([
                "Message" => "Updated correctly"
            ]);
        } else {
            return response()->json([
                "Status" => "Not found"
            ], 404);
        };
    }

    public function destroy($id)
    {
        if(Pista::where('id', $id)->exists()) {
            $pistas = Pista::find($id);
            $pistas->delete();
            return response()->json([
                "message" => "Pista deleted"
            ], 202);
        } else {
            return response()->json([
                "message" => "Pista not found"
            ], 404);
        }
    }
}