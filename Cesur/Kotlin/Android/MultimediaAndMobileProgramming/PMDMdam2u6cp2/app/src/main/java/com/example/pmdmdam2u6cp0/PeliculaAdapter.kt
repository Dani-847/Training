// Kotlin
// `app/src/main/java/com/example/pmdmdam2u6cp0/PeliculaAdapter.kt`
package com.example.pmdmdam2u6cp0

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PeliculaAdapter(
    private val items: MutableList<Pelicula>
) : RecyclerView.Adapter<PeliculaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pelicula, parent, false)
        return PeliculaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        holder.render(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun limpiar() { items.clear() }

    fun recargar(): List<Pelicula> = PeliculaProvider.listaCarga
}
