package com.example.primerproyecto

fun main() {
    print("¿Cómo es el clima de hoy? Ingresa un número del 1 al 5: ")
    val clima = readLine()?.toIntOrNull() ?: 0

    val descripcion = when (clima) {
        1 -> "Soleado"
        2 -> "Nublado"
        3 -> "Lluvioso"
        4 -> "Tormentoso"
        5 -> "Nevado"
        else -> "Pregúntale a Google"
    }

    println(descripcion)
}
