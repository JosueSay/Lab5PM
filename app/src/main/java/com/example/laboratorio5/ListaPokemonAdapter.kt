package com.example.laboratorio5

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.laboratorio5.Models.Pokemon

class ListaPokemonAdapter(private val context: Context) :

    RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder>() {
    private val dataset: ArrayList<Pokemon>
    private val urlIMG = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
    private val TAG = "POKEMONS"                             // Mensaje en inconexion

    init {
        dataset = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p = dataset[position]
        Log.e(TAG, "- SOY EL POKEMON: " + p.dameNumero())

        holder.nombreTextView.text = p.name
        Glide.with(context)
            .load(urlIMG + p.dameNumero() + ".png")
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.fotoImageView);
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    fun adicionarListaPokemon(listaPokemon: ArrayList<Pokemon>?) {
        dataset.addAll(listaPokemon!!)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fotoImageView: ImageView
        val nombreTextView: TextView

        init {
            fotoImageView = itemView.findViewById(R.id.fotoImageView)
            nombreTextView = itemView.findViewById(R.id.nombreTextView)
        }
    }
}
