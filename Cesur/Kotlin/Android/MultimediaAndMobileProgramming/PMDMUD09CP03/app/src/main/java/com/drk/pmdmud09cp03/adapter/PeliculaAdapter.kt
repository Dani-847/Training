package com.drk.pmdmud09cp03.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drk.pmdmud09cp03.R
import com.drk.pmdmud09cp03.model.Pelicula

class PeliculaAdapter(
    private var listaPeliculas: List<Pelicula>,
    private val onClickListener: (Pelicula) -> Unit,
    private val onLongClickListener: (Pelicula) -> Boolean
) : RecyclerView.Adapter<PeliculaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PeliculaViewHolder(
            layoutInflater.inflate(R.layout.item_pelicula, parent, false)
        )
    }

    override fun getItemCount(): Int = listaPeliculas.size

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        val item = listaPeliculas[position]
        holder.render(item, onClickListener, onLongClickListener)
    }

    fun actualizarPeliculas(nuevaLista: List<Pelicula>) {
        listaPeliculas = nuevaLista
        notifyDataSetChanged()
    }
}
