package com.drk.mistareaspro.data

data class Tarea(
    val id: Long = 0,
    val titulo: String,
    val descripcion: String,
    val fecha: String,
    val prioridad: Int,
    val completada: Boolean = false
)
