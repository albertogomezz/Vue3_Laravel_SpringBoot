<?php

namespace App\Http\Resources;

use Illuminate\Http\Resources\Json\JsonResource;

class PistaResource extends JsonResource
{
    public function toArray($request)
    {
        return [
            'id' => $this->id,
            'pista_id' => $this->pista_id,
            'description' => $this->description,
            'image' => $this->image,
            'type' => $this->type,
            'is_reserved' => $this->is_reserved
        ];
    }
}
