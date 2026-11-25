package com.example.proyecto26_09_25.Ej3

fun main() {
    val fresco = ProductoFresco("2025-11-10", "L001", "2025-09-20", "España")
    val refrigerado = ProductoRefrigerado("2025-12-05", "L145", "RS-AGRO-7788")
    val congelado = ProductoCongelado("2026-03-15", "L900", -22.5)

    println(fresco.info())
    println(refrigerado.info())
    println(congelado.info())
}
