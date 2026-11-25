package com.example.proyecto26_09_25.Ej1

fun main() {
    var x = SerVivo(3)
    var y = SerVivo(5)
    val mayorSerVivo = x.mayor(y)
    println("Mayor SerVivo: $mayorSerVivo")

    x = Humano("Homero", 34)
    y = Humano("Bart", 9)
    val mayorHumano = x.mayor(y)
    println("Mayor Humano: $mayorHumano")
}
