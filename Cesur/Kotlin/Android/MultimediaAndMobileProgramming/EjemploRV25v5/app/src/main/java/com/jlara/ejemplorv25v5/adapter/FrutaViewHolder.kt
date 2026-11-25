package com.jlara.ejemplorv25v5.adapter

import android.view.ContextMenu
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jlara.ejemplorv25v5.model.Fruta
import com.jlara.ejemplorv25v5.databinding.ItemFrutaBinding
import com.squareup.picasso.Picasso

class FrutaViewHolder(view: View):ViewHolder(view) , View.OnCreateContextMenuListener{

    private val binding= ItemFrutaBinding.bind(view)
    private lateinit var fruta:Fruta

    fun render(item: Fruta, onClickListener: (Fruta)->Unit) {
        fruta=item
        binding.tvFrutaNombre.text=item.nombre
        binding.tvDescripcion.text=item.descripcion
        //binding.ivFruta.setImageResource(item.imagen)
        //Picasso.get().load(item.imagen).fit().into(binding.ivFruta)
        //Picasso.get().load(item.imagen).resize(80,80).into(binding.ivFruta)
        Picasso.get().load(item.imagen).resize(80,80).centerInside().into(binding.ivFruta)
        //Glide.with(binding.ivFruta.context).load(item.imagen).into(binding.ivFruta)
        //Glide.with(binding.ivFruta.context).load(item.imagen).fitCenter().into(binding.ivFruta)
        //Glide.with(binding.ivFruta.context).load(item.imagen).centerCrop().into(binding.ivFruta)
        //Glide.with(binding.ivFruta.context).load(item.imagen).fitCenter().placeholder(R.drawable.no_imagen).error(ColorDrawable(
        //    Color.RED)).into(binding.ivFruta)
        itemView.setOnClickListener {
            onClickListener(item)
        }
        itemView.setOnCreateContextMenuListener(this)
    }

    override fun onCreateContextMenu(
        p0: ContextMenu?,
        p1: View?,
        p2: ContextMenu.ContextMenuInfo?
    ) {
        p0!!.setHeaderTitle(fruta.nombre)
        p0.add(this.adapterPosition, 0, 0, "Eliminar")
    }
}

