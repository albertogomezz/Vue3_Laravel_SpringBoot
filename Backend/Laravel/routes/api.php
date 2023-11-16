<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\PistaController;
use App\Http\Controllers\SportController;

Route::resource('pistas', PistaController::class);
Route::resource('sports', SportController::class);

// Route::post('/pistas', [PistaController::class, 'store']);
// Route::get('/pistas', [PistaController::class, 'index']);
// Route::get('/pistas/{id}', [PistaController::class, 'show']);
// Route::put('/pistas/{id}', [PistaController::class, 'update']);
// Route::delete('/pistas/{id}', [PistaController::class, 'destroy']);
