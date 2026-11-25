package com.example.primerproyecto

fun main() {
    print("Ingresa un número para calcular la sumatoria: ")
    val n = readLine()?.toIntOrNull() ?: 0

    // Usando for
    var sumaFor = 0
    for (i in 1..n) {
        sumaFor += i
    }
    println("La suma con for es $sumaFor")

    // Usando while
    var sumaWhile = 0
    var i = 1
    while (i <= n) {
        sumaWhile += i
        i++
    }
    println("La suma con while es $sumaWhile")
}
