package com.example.primerproyecto

fun main() {
    var listo = false

    while (!listo) {
        print("Ingresa la distancia del conductor en km: ")
        val distancia = readLine()?.toDoubleOrNull() ?: 1.0

        print("¿Está disponible el conductor? (true/false): ")
        val disponible = readLine()?.toBooleanStrictOrNull() ?: false

        if (distancia <= 0.5 && disponible) {
            println("Listo para iniciar recorrido")
            listo = true
        } else if (distancia <= 0.5 && !disponible) {
            println("Conductor cercano, pero no disponible")
        } else if (distancia > 0.5 && disponible) {
            println("Conductor disponible pero muy lejos, aplicarán tarifas más altas")
        } else {
            println("No hay conductores disponibles")
        }
    }
}
