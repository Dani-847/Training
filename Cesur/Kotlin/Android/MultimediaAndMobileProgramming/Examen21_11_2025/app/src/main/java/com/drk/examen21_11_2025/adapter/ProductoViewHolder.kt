package com.drk.examen21_11_2025.adapter

import android.view.ContextMenu
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.drk.examen21_11_2025.model.Producto
import com.drk.examen21_11_2025.databinding.ItemProductoBinding

class ProductoViewHolder (view: View): RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {
    private val binding= ItemProductoBinding.bind(view)
    private lateinit var producto: Producto
    fun render(item: Producto, onClickListener:(Producto)->Unit){
        producto=item
        binding.tvNombre.text=item.nombre
        binding.ivImagen.setImageResource(item.imagen)
        itemView.setOnClickListener { onClickListener(item) }
        itemView.setOnCreateContextMenuListener(this)
    }

    override fun onCreateContextMenu(
        p0: ContextMenu?,
        p1: View?,
        p2: ContextMenu.ContextMenuInfo?
    ) {
        p0!!.setHeaderTitle(producto.nombre)
        p0.add(this.bindingAdapterPosition,0,0,"Eliminar")
        val add = p0.add(this.bindingAdapterPosition, 1, 1, "Editar")
    }

}