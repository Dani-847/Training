package com.drk.pmdmud13cp01.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drk.pmdmud13cp01.model.Pelicula
import com.drk.pmdmud13cp01.R

class PeliculaAdapter(private var peliculas:MutableList<Pelicula>, private val onClickListener:(Pelicula)->Unit):
    RecyclerView.Adapter<PeliculaViewHolder>() {

    fun updateData(newPeliculas: List<Pelicula>) {
            var tamanho = peliculas.size
            peliculas.clear()
            notifyItemRangeRemoved(0,tamanho)
            peliculas.addAll(newPeliculas)
            tamanho = peliculas.size
            notifyItemRangeInserted(0,tamanho)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_pelicula, parent, false)
        return PeliculaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        holder.bind(peliculas[position], onClickListener)
    }

    override fun getItemCount() = peliculas.size
}

