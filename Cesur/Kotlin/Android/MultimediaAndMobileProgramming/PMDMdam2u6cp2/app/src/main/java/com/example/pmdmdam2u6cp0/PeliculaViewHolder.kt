package com.example.pmdmdam2u6cp0

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class PeliculaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val foto: ImageView = itemView.findViewById(R.id.ivPelicula)
    private val nombre: TextView = itemView.findViewById(R.id.txtNombre)

    fun render(pelicula: Pelicula) {
        foto.setImageResource(pelicula.fotoResId)
        nombre.text = pelicula.nombre

        itemView.setOnClickListener {
            val msg = "Duración: ${pelicula.duracion} \u2013 Año: ${pelicula.anioEstreno}"
            Toast.makeText(itemView.context, msg, Toast.LENGTH_SHORT).show()
        }
    }
}
