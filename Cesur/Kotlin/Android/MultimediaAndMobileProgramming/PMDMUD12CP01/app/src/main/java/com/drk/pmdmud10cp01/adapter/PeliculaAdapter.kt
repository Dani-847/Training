package com.drk.pmdmud10cp01.adapter

import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.drk.pmdmud10cp01.R
import com.drk.pmdmud10cp01.databinding.ItemPeliculaBinding
import com.drk.pmdmud10cp01.model.Pelicula

class PeliculaAdapter(
    private var peliculas: MutableList<Pelicula>,
    private val onItemClick: (Pelicula) -> Unit
) : RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder>() {

    var posicionSeleccionada: Int = -1

    inner class PeliculaViewHolder(private val binding: ItemPeliculaBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnCreateContextMenuListener {

        init {
            itemView.setOnCreateContextMenuListener(this)
        }

        fun bind(pelicula: Pelicula) {
            binding.tvTitulo.text = pelicula.titulo
            binding.tvDirector.text = pelicula.director
            binding.tvGenero.text = pelicula.genero
            binding.tvAnio.text = pelicula.anio.toString()

            if (pelicula.imagenUrl.isNotEmpty()) {
                binding.ivPelicula.load(pelicula.imagenUrl) {
                    placeholder(R.drawable.no_imagen)
                    error(R.drawable.no_imagen)
                }
            } else {
                binding.ivPelicula.setImageResource(R.drawable.no_imagen)
            }

            itemView.setOnClickListener { onItemClick(pelicula) }

            itemView.setOnLongClickListener {
                posicionSeleccionada = adapterPosition
                false
            }
        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            menu?.setHeaderTitle(peliculas[adapterPosition].titulo)
            menu?.add(0, R.id.menu_detalle, 0, "Detalle")
            menu?.add(0, R.id.menu_editar, 1, "Editar")
            menu?.add(0, R.id.menu_eliminar, 2, "Eliminar")
            menu?.add(0, R.id.menu_hacer_foto, 3, "Hacer foto")
            menu?.add(0, R.id.menu_ver_foto, 4, "Ver foto")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        val binding = ItemPeliculaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PeliculaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        holder.bind(peliculas[position])
    }

    override fun getItemCount(): Int = peliculas.size

    fun getPelicula(position: Int): Pelicula = peliculas[position]

    fun setFilteredList(lista: MutableList<Pelicula>) {
        peliculas = lista
        notifyDataSetChanged()
    }
}
