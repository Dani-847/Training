package com.drk.pmdmud10cp01.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "peliculas")
data class Pelicula(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val titulo: String,
    val director: String,
    val anio: Int,
    val genero: String,
    val sinopsis: String,
    val imagenUrl: String = "",
    val fotoPath: String = "",
    val latitud: Double = 0.0,
    val longitud: Double = 0.0
)
