package com.example.proyecto26_09_25.Ej2

class Persona(nombre: String, apellido: String, telefono: String) {
    var nombre: String = nombre
        set(value) {
            require(value.isNotBlank()) { "Nombre no puede estar vacío." }
            field = value
        }

    var apellido: String = apellido
        set(value) {
            require(value.isNotBlank()) { "Apellido no puede estar vacío." }
            field = value
        }

    var telefono: String = telefono
        set(value) {
            require(value.matches(Regex("^\\d{9}$"))) { "El teléfono debe tener exactamente 9 dígitos." }
            field = value
        }

    init {
        this.nombre = nombre
        this.apellido = apellido
        this.telefono = telefono
    }

    override fun toString(): String =
        "Persona(nombre=$nombre, apellido=$apellido, telefono=$telefono)"
}
