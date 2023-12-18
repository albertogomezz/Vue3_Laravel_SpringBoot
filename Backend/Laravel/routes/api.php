<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\PistaController;
use App\Http\Controllers\SportController;
use App\Http\Controllers\UserController;

Route::group(['middleware' => ['admin']], function () {
    Route::resource('pistas', PistaController::class);
    Route::resource('sports', SportController::class);
    Route::resource('users', UserController::class);
});

Route::post('login', [UserController::class, 'login']);
Route::post('logout', [UserController::class, 'logout']);
Route::get('profile', [UserController::class, 'getUserToken']);
Route::get('isAdmin', [UserController::class, 'isAdmin']);

// Route::post('/pistas', [PistaController::class, 'store']);
// Route::get('/pistas', [PistaController::class, 'index']);
// Route::get('/pistas/{id}', [PistaController::class, 'show']);
// Route::put('/pistas/{id}', [PistaController::class, 'update']);
// Route::delete('/pistas/{id}', [PistaController::class, 'destroy']);
