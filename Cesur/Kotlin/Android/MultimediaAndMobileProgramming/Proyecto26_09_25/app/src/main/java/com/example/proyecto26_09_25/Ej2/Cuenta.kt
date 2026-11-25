package com.example.proyecto26_09_25.Ej2

class Cuenta(numeroCuenta: String, saldo: Double, propietario: Persona) {
    var numeroCuenta: String = numeroCuenta
        set(value) {
            require(value.isNotBlank()) { "Número de cuenta inválido." }
            field = value
        }

    var saldo: Double = saldo
        set(value) {
            require(value >= 0.0) { "El saldo no puede ser negativo." }
            field = value
        }

    var propietario: Persona = propietario

    constructor(): this(
        numeroCuenta = "SIN_NUMERO",
        saldo = 0.0,
        propietario = Persona("SIN_NOMBRE", "SIN_APELLIDO", "000000000")
    )

    fun transaccion(cantidad: Double, tipoTransaccion: String) {
        require(cantidad > 0) { "La cantidad debe ser positiva." }
        when (tipoTransaccion.lowercase()) {
            "ingreso" -> {
                saldo += cantidad
                println("Transacción: ingreso | Nuevo saldo: $saldo")
            }
            "reintegro" -> {
                require(saldo - cantidad >= 0) { "Saldo insuficiente para reintegro." }
                saldo -= cantidad
                println("Transacción: reintegro | Nuevo saldo: $saldo")
            }
            else -> error("Tipo de transacción inválido.")
        }
    }

    override fun toString(): String =
        "Cuenta(numeroCuenta=$numeroCuenta, saldo=$saldo, propietario=$propietario)"
}
