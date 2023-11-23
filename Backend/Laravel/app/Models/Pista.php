<?php

namespace App\Models;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use App\Models\Sport;
use Illuminate\Database\Eloquent\Relations\BelongsToMany;

class Pista extends Model
{
    use HasFactory;

    protected $table = 'pista';
    protected $fillable = ['pista_id','description','image','type','is_reserved'];

    public function sports(): BelongsToMany {

        return $this->belongsToMany(Sport::class, 'pistas_sports', 'pista_id', 'sport_id');

    }
}