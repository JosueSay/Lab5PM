package com.example.laboratorio5.PokeApi

import com.example.laboratorio5.Models.PokemonRespuesta
import retrofit2.Call
import retrofit2.http.GET

interface PokeApiService {
    @GET("pokemon")
    fun obtenerListaPokemon(): Call<PokemonRespuesta?>?
}