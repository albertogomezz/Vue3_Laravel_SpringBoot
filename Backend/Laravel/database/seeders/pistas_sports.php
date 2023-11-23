<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

use App\Models\Sport;
use App\Models\Pista;

class pistas_sports extends Seeder
{   
    public function run(): void
    {

        $sports = Sport::factory()->count(5)->create();
        $pistas = Pista::factory()->count(50)->create();

        foreach ($pistas as $pista) {
            $pista->sport()->attach($sports->random(rand(1, 3)));
        }
    }
}
