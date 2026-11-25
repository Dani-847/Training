package com.jlara.ejemplorv25v5.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jlara.ejemplorv25v5.model.Fruta
import com.jlara.ejemplorv25v5.R

class FrutaAdapter(private var frutasLista:List<Fruta>,
    private val onClickListener: (Fruta)->Unit): RecyclerView.Adapter<FrutaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrutaViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        return FrutaViewHolder(layoutInflater.inflate(R.layout.item_fruta,parent,false))
    }

    override fun getItemCount(): Int {
        return frutasLista.size
    }

    override fun onBindViewHolder(holder: FrutaViewHolder, position: Int) {
        val item=frutasLista[position]
        holder.render(item, onClickListener)
    }

    fun setFilteredList(mList: MutableList<Fruta>){
        notifyItemRangeRemoved(0,mList.size)
        frutasLista=mList
        notifyItemRangeInserted(0,mList.size)
    }
}

