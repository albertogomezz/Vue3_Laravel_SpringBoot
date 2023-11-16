<?php

namespace App\Http\Requests\Sport;

use Illuminate\Foundation\Http\FormRequest;

class StoreSportRequest extends FormRequest
{

    public function authorize()
    {
        return true;
        // return false;
    }

    public function rules()
    {
        return [
            'sport_id' => 'required|string|max:255',
            'sport_name' => 'required|string|max:255',
            'description' => 'required|string|max:255',
            'price' => 'required|string|max:255',
            'image' => 'required|string|max:255'
        ];
    }
}
