<?php

namespace App\Http\Resources;

use Illuminate\Http\Resources\Json\JsonResource;

class SportResource extends JsonResource
{
    public function toArray($request)
    {
        return [
            'id' => $this->id,
            'sport_id' => $this->sport_id,
            'sport_name' => $this->sport_name,
            'description' => $this->description,
            'price' => $this->price,
            'image' => $this->image,
        ];
    }
}
