<?php

namespace App\Http\Controllers;
use Illuminate\Http\Request;
use App\Models\Pista;

//RESOURCE
use App\Http\Resources\PistaResource;

//REQUEST
use App\Http\Requests\Pista\StorePistaRequest;
use App\Http\Requests\Pista\UpdatePistaRequest;

class PistaController extends Controller
{   

    public function store(StorePistaRequest $request) {
        return PistaResource::make(Pista::create($request->validated()));
    }

    public function index() {   
        return PistaResource::collection(Pista::all());
        //   return PistaResource::collection(Student::limit($limit)->offset(($page - 1) * $limit)->get());
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
        if (Pista::where('id', $id)->exists()) {

            $pista = Pista::find($id);
            $pista->update($request->validated());
            return PistaResource::make($pista);
            
        } else {
            return response()->json([
                "message" => "Pista not found"
            ], 404);
        } 
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