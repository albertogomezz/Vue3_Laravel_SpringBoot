<?php

namespace App\Models;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Pista extends Model
{
    use HasFactory;

    protected $table = 'pista';
    protected $fillable = ['pista_id','description','image','type','is_reserved'];
}