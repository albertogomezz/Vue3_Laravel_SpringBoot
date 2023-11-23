<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;
use Illuminate\Support\Facades\DB;

return new class extends Migration
{
    public function up()
    {
        Schema::create('pistas_sports', function (Blueprint $table) {
            $table->id();
            $table->foreignId('pista_id')->constrained('pista')->onDelete('cascade');
            $table->foreignId('sport_id')->constrained('sport')->onDelete('cascade');
            $table->timestamp('created_at')->default(DB::raw('CURRENT_TIMESTAMP'));
        });
    }
};
