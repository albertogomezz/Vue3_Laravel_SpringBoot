<?php


namespace App\Http\Controllers;
use Illuminate\Http\Request;
use App\Models\Sport;

//RESOURCE
use App\Http\Resources\SportResource;

//REQUEST
use App\Http\Requests\Sport\StoreSportRequest;
use App\Http\Requests\Sport\UpdateSportRequest;

class SportController extends Controller
{   

    public function store(StoreSportRequest $request) {
        return SportResource::make(Sport::create($request->validated()));
    }

    public function index() {   
        return SportResource::collection(Sport::all());
        //   return PistaResource::collection(Student::limit($limit)->offset(($page - 1) * $limit)->get());
    }

    public function show($id) {

        if(Sport::where('id', $id)->exists()) {
            return SportResource::make(Sport::where('id', $id)->firstOrFail());
        } else {
            return response()->json([
                "message" => "Sport not found"
            ], 404);
        }
    }

    public function update(UpdateSportRequest $request, $id)
    {
        if (Sport::where('id', $id)->exists()) {

            $sport = Sport::find($id);
            $sport->update($request->validated());
            return SportResource::make($sport);
            
        } else {
            return response()->json([
                "message" => "Sport not found"
            ], 404);
        } 
    }

    public function destroy($id)
    {
        if(Sport::where('id', $id)->exists()) {
            $sport = Sport::find($id);
            $sport->delete();
            return response()->json([
                "message" => "Sport deleted"
            ], 202);
        } else {
            return response()->json([
                "message" => "Sport not found"
            ], 404);
        }
    }
}