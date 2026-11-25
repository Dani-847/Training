package com.example.pmdmdam2u6cp0

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pmdmdam2u6cp0.databinding.ItemPersonaBinding

class PersonaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemPersonaBinding.bind(view)

    fun render(item: Persona) {
        binding.ivPersona.setImageResource(item.fotoResId)
        binding.txtNombre.text = item.nombre
        binding.txtDescripcion.text = item.descripcion
    }
}
