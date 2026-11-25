package com.drk.examen21_11_2025.model

import java.io.Serializable

data class Producto (
    val id: Int,
    var nombre:String,
    val imagen:Int,
    var precio:Double,
    var categoria:String,
    var descripcion:String
): Serializable