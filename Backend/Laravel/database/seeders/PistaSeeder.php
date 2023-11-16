<?php

namespace Database\Seeders;
use App\Models\Pista;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class PistaSeeder extends Seeder
{
    public function run(): void
    {
        Pista::factory()
            ->count(10)
            ->create();
    }
}