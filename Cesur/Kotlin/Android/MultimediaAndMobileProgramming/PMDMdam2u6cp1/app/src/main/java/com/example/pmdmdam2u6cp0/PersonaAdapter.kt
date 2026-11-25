package com.example.pmdmdam2u6cp0

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class PersonaAdapter(
    private val personas: List<Persona>
) : RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_persona, parent, false)
        return PersonaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        holder.bind(personas[position])
    }

    override fun getItemCount(): Int = personas.size

    inner class PersonaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivFoto: ImageView = itemView.findViewById(R.id.ivPersona)
        private val tvNombre: TextView = itemView.findViewById(R.id.txtNombre)
        private val tvDescripcion: TextView = itemView.findViewById(R.id.txtDescripcion)

        fun bind(persona: Persona) {
            tvNombre.text = persona.nombre
            tvDescripcion.text = persona.descripcion
            ivFoto.setImageResource(persona.fotoResId)

            itemView.setOnClickListener {
                val msg = "Has pulsado sobre ${persona.nombre} - Año de nacimiento: ${persona.anioNacimiento} - Ciudad: ${persona.ciudad}"
                Toast.makeText(itemView.context, msg, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
