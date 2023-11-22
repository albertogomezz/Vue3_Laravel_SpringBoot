<?php

namespace Database\Factories;

use App\Models\Pista;
use Illuminate\Database\Eloquent\Factories\Factory;

class PistaFactory extends Factory
{

    protected $model = Pista::class;

    public function definition()
    {
        return [
            'pista_id' => $this->faker->firstName(),
            'description' => $this->faker->paragraph(),
            'image' => $this->faker->imageURL(640, 400, 'animals', true),
            'type' => $this->faker->firstName(),
            'is_reserved' => $this->faker->boolean()
        ];
    }
}