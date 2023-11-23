<?php

namespace App\Http\Requests\Pista;

use Illuminate\Foundation\Http\FormRequest;

class StorePistaRequest extends FormRequest
{

    public function authorize()
    {
        return true;
        // return false;
    }

    public function rules()
    {
        return [
            'pista_id' => 'required|string|max:255',
            'description' => 'required|string|max:255',
            'image' => 'required|string|max:255',
            'type' => 'required|string|max:255',
            'is_reserved' => 'required|boolean',
            'sports' => 'required','array:name,sports',
        ];
    }
}