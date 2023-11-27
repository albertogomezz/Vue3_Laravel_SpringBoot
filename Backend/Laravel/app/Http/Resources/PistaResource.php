<?php

namespace App\Http\Resources;

use Illuminate\Http\Resources\Json\JsonResource;

class PistaResource extends JsonResource
{
    public function toArray($request)
    {
        $sports = [];
        foreach ($this->sports as $c) {
            $sport = [
                "id" => $c->id,
                "sport_id" => $c->sport_id,
                "sport_name" => $c->sport_name,
                "description" => $c->description,
                "price" => $c->price,
                "image" => $c->image
            ];
            array_push($sports, $sport);
        }

        return [
            'id' => $this->id,
            'pista_id' => $this->pista_id,
            'description' => $this->description,
            'image' => $this->image,
            'type' => $this->type,
            'is_reserved' => $this->is_reserved,
            'sports' => $sports
        ];
    }
}
