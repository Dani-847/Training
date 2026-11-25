package com.drk.examen21_11_2025.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drk.examen21_11_2025.model.Producto
import com.drk.examen21_11_2025.R

class ProductoAdapter(private var listaProductos: List<Producto>, var onClickListener: (Producto)->Unit): RecyclerView.Adapter<ProductoViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductoViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return ProductoViewHolder(layoutInflater.inflate(R.layout.item_producto, parent, false))
    }

    override fun onBindViewHolder(
        holder: ProductoViewHolder,
        position: Int
    ) {
        val item=listaProductos[position]
        holder.render(item,onClickListener)

    }

    override fun getItemCount(): Int {
        return listaProductos.size
    }
    fun setFilteredList(mList: MutableList<Producto>){
        notifyItemRangeRemoved(0,mList.size)
        listaProductos=mList
        notifyItemRangeInserted(0,mList.size)
    }
}