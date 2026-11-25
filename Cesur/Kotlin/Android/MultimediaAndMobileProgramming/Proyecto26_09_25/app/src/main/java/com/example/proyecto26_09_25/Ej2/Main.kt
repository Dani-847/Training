package com.example.proyecto26_09_25.Ej2

fun main() {
    val persona1 = Persona("Ana", "López", "123456789")
    val persona2 = Persona("Luis", "Martín", "987654321")

    val cuenta1 = Cuenta("ACC1001", 500.0, persona1)
    val cuenta2 = Cuenta("ACC2002", 1200.0, persona2)

    println(persona1)
    println(persona2)
    println(cuenta1)
    println(cuenta2)

    cuenta1.transaccion(200.0, "ingreso")
    cuenta1.transaccion(150.0, "reintegro")

    cuenta2.transaccion(500.0, "reintegro")
    cuenta2.transaccion(300.0, "ingreso")

    println("Estado final:")
    println(cuenta1)
    println(cuenta2)
}
