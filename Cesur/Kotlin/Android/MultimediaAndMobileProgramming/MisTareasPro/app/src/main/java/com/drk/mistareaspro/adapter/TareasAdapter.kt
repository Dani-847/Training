package com.drk.mistareaspro.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.drk.mistareaspro.R
import com.drk.mistareaspro.data.Tarea

class TareasAdapter(
    private var tareas: MutableList<Tarea>,
    private val onEdit: (Tarea) -> Unit,
    private val onDelete: (Tarea) -> Unit,
    private val onComplete: (Tarea, Boolean) -> Unit
) : RecyclerView.Adapter<TareasAdapter.TareaViewHolder>() {

    inner class TareaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitulo: TextView = view.findViewById(R.id.tvTitulo)
        val tvFecha: TextView = view.findViewById(R.id.tvFecha)
        val cbCompletada: CheckBox = view.findViewById(R.id.cbCompletada)
        val ivEdit: ImageView = view.findViewById(R.id.ivEdit)
        val ivDelete: ImageView = view.findViewById(R.id.ivDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tarea, parent, false)
        return TareaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        val tarea = tareas[position]
        holder.tvTitulo.text = tarea.titulo
        holder.tvFecha.text = tarea.fecha
        holder.cbCompletada.isChecked = tarea.completada

        holder.cbCompletada.setOnCheckedChangeListener { _, isChecked ->
            onComplete(tarea, isChecked)
        }
        holder.ivEdit.setOnClickListener { onEdit(tarea) }
        holder.ivDelete.setOnClickListener { onDelete(tarea) }
    }

    override fun getItemCount() = tareas.size

    fun updateList(newTareas: List<Tarea>) {
        tareas.clear()
        tareas.addAll(newTareas)
        notifyDataSetChanged()
    }
}
