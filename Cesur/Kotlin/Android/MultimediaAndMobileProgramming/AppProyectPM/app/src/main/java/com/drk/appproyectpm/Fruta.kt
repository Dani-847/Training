package com.drk.appproyectpm

import java.io.Serializable

data class Fruta(
    val nombre: String,
    val descripcion: String,
    val imagen: Int,
    val precioPorKilo: Double,
    val origen: String,
    val temporada: String,
    val color: String,
    val valorNutricional: String
): Serializable