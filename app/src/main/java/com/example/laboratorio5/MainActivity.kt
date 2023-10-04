package com.example.laboratorio5

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.laboratorio5.Models.Pokemon
import com.example.laboratorio5.Models.PokemonRespuesta
import com.example.laboratorio5.PokeApi.PokeApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    // Variables para obtener el listado de pokemons
    private var retrofit: Retrofit? = null                  //variable retrofit
    private val urlBase = "https://pokeapi.co/api/v2/"      // URL Base
    private val TAG = "POKEDEX"                             // Mensaje en inconexion
    private var recyclerView: RecyclerView? = null
    private var listaPokemonAdapter: ListaPokemonAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        listaPokemonAdapter = ListaPokemonAdapter(this)

        recyclerView?.let {
            it.adapter = listaPokemonAdapter
            it.setHasFixedSize(true)
            val layoutManager = GridLayoutManager(this, 3)
            it.layoutManager = layoutManager
        }

        // instanciar retrofit
        retrofit = Retrofit
            .Builder()
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        obtenerDatos()
    }

    private fun obtenerDatos() {
        val service: PokeApiService = retrofit!!.create(PokeApiService::class.java)
        val pokemonRespuestaCall: Call<PokemonRespuesta?>? = service.obtenerListaPokemon()

        pokemonRespuestaCall?.enqueue(object : Callback<PokemonRespuesta?> {
            override fun onResponse(
                call: Call<PokemonRespuesta?>,
                response: Response<PokemonRespuesta?>
            ) {
                if (response.isSuccessful) {

                    val pokemonRespuesta = response.body()
                    val listaPokemon: ArrayList<Pokemon>? = pokemonRespuesta?.results
                    listaPokemonAdapter!!.adicionarListaPokemon(listaPokemon)

                } else {
                    Log.e(TAG, "onResponse: " + response.errorBody())
                }
            }

            override fun onFailure(call: Call<PokemonRespuesta?>, t: Throwable) {}
        })
    }


}