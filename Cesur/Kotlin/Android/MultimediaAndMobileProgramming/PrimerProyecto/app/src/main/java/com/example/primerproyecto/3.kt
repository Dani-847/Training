package com.example.primerproyecto

fun main() {
    val peliculas = arrayOf("Jumanji", "Toy Story", "Pulp Fiction", "Batman: El caballero de la noche", "Kill Bill")

    var tituloMasLargo = ""
    var indiceMasLargo = -1

    for ((index, titulo) in peliculas.withIndex()) {
        if (titulo.length > tituloMasLargo.length) {
            tituloMasLargo = titulo
            indiceMasLargo = index
        }
    }

    println("El título más largo es: \"$tituloMasLargo\"")
    println("Índice del título más largo: $indiceMasLargo")
}
