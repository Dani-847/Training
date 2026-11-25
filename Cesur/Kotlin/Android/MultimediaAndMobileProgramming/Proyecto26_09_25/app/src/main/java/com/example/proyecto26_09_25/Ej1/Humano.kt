package com.example.proyecto26_09_25.Ej1

class Humano(val nombre: String, edad: Int) : SerVivo(edad) {

    override fun mayor(other: SerVivo): SerVivo {
        if (other !is Humano) return super.mayor(other)
        return when {
            this.edad > other.edad -> this
            this.edad < other.edad -> other
            this.nombre.length >= other.nombre.length -> this
            else -> other
        }
    }

    override fun toString(): String {
        return "Humano(nombre=$nombre, edad=$edad)"
    }
        

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Humano) return false
        return edad == other.edad && nombre == other.nombre
    }

    override fun hashCode(): Int {
        return 31 * edad + nombre.hashCode()
    }
}
