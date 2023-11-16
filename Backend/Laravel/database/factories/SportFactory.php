<?php

namespace Database\Factories;

use App\Models\Sport;
use Illuminate\Database\Eloquent\Factories\Factory;

class SportFactory extends Factory
{

    protected $model = Sport::class;

    public function definition()
    {
        return [
            'sport_id' => $this->faker->firstName(),
            'sport_name' => $this->faker->firstName(),
            'description' => $this->faker->paragraph(),
            'price' => $this->faker->numberBetween(),
            'image' => $this->faker->imageURL(640, 400, 'animals', true),
        ];
    }
}