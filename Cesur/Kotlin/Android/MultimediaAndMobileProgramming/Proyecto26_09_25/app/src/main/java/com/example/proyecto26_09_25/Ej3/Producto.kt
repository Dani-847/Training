package com.example.proyecto26_09_25.Ej3

open class Producto(
    var fechaCaducidad: String,
    var numeroLote: String
) {
    constructor(): this("SIN_FECHA", "SIN_LOTE")

    open fun info(): String =
        "Producto(fechaCaducidad=$fechaCaducidad, numeroLote=$numeroLote)"

    override fun toString(): String = info()
}
