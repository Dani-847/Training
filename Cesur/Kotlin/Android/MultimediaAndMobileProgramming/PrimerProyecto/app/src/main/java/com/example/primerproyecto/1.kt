package com.example.primerproyecto

fun main() {
    print("Ingresa la cantidad en miligramos de la poción: ")
    val miligramos = readLine()?.toIntOrNull() ?: 0

    if (miligramos > 100) {
        println("¡Felicidades, es una buena poción!")
    } else {
        println("La poción es mediocre, sangre sucia inmunda")
    }
}
