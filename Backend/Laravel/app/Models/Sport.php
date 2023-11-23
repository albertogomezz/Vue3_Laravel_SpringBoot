<?php

namespace App\Models;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use App\Models\Pista;
use Illuminate\Database\Eloquent\Relations\BelongsToMany;

class Sport extends Model
{
    use HasFactory;

    protected $table = 'sport';
    protected $fillable = ['sport_id','sport_name','description','price','image'];

    public function pistas(): BelongsToMany
    {
        return $this->belongsToMany(Pista::class, 'pistas_sports', 'sports_id', 'pista_id');
    }
}