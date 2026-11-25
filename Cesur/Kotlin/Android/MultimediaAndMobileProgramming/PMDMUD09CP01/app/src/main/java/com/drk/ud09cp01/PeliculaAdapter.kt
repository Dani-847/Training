package com.drk.ud09cp01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PeliculaAdapter(
    private var listaPeliculas: MutableList<Pelicula>,
    private val onClickListener: (Pelicula) -> Unit,
    private val onLongClickListener: (Pelicula, View) -> Boolean
) : RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder>() {

    class PeliculaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen: ImageView = itemView.findViewById(R.id.ivPelicula)
        val titulo: TextView = itemView.findViewById(R.id.tvTitulo)

        fun bind(pelicula: Pelicula, onClickListener: (Pelicula) -> Unit, onLongClickListener: (Pelicula, View) -> Boolean) {
            itemView.setOnClickListener { onClickListener(pelicula) }
            itemView.setOnLongClickListener { onLongClickListener(pelicula, itemView) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pelicula, parent, false)
        return PeliculaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        val pelicula = listaPeliculas[position]

        holder.titulo.text = pelicula.titulo
        holder.imagen.setImageResource(pelicula.img)

        holder.bind(pelicula, onClickListener, onLongClickListener)
    }

    override fun getItemCount(): Int = listaPeliculas.size

    fun actualizarPeliculas(nuevasPeliculas: List<Pelicula>) {
        listaPeliculas.clear()
        listaPeliculas.addAll(nuevasPeliculas)
        notifyDataSetChanged()
    }
}
