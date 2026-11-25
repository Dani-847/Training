package com.drk.pmdmud09cp03.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.drk.pmdmud09cp03.databinding.ItemPeliculaBinding
import com.drk.pmdmud09cp03.model.Pelicula

class PeliculaViewHolder(view: View) : ViewHolder(view) {

    private val binding = ItemPeliculaBinding.bind(view)

    fun render(
            pelicula: Pelicula,
            onClickListener: (Pelicula) -> Unit,
            onLongClickListener: (Pelicula) -> Boolean
    ) {
        binding.tvTitulo.text = pelicula.titulo
        binding.ivPelicula.setImageResource(pelicula.img)

        itemView.setOnClickListener {
            onClickListener(pelicula)
        }

        itemView.setOnLongClickListener {
            onLongClickListener(pelicula)
        }
    }
}
