package com.drk.pmdmud13cp01.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.drk.pmdmud13cp01.R
import com.drk.pmdmud13cp01.model.Pelicula
import com.drk.pmdmud13cp01.databinding.ItemPeliculaBinding
import com.squareup.picasso.Picasso

class PeliculaViewHolder(view: View): ViewHolder(view) {
    private val binding = ItemPeliculaBinding.bind(view)

    fun bind(pelicula: Pelicula, onClickListener:(Pelicula)->Unit) {
        binding.tvPeliculaTitulo.text = pelicula.title

        val posterUrl = if (!pelicula.posterPath.isNullOrEmpty()) {
            "https://image.tmdb.org/t/p/w500${pelicula.posterPath}"
        } else {
            null
        }

        Picasso.get()
            .load(posterUrl)
            .placeholder(R.drawable.imagen_placeholder)
            .error(R.drawable.noposterp)
            .into(binding.ivPeliculaPoster)

        itemView.setOnClickListener {
            onClickListener(pelicula)
        }
    }
}

