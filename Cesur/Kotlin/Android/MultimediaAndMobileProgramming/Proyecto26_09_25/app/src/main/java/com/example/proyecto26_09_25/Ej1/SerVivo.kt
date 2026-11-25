package com.example.proyecto26_09_25.Ej1

open class SerVivo(val edad: Int) {
    open fun mayor(other: SerVivo): SerVivo {
        if (this.edad >= other.edad)
            return this
        else
            return other
    }

    override fun toString(): String {
        return "SerVivo(edad=$edad)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SerVivo) return false
        return edad == other.edad
    }

    override fun hashCode(): Int {
        return edad.hashCode()
    }
}
