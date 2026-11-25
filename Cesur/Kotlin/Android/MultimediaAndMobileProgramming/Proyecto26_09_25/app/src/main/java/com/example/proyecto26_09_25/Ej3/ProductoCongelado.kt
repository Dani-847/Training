package com.example.proyecto26_09_25.Ej3

class ProductoCongelado(
    fechaCaducidad: String,
    numeroLote: String,
    var temperaturaRecomendada: Double
) : Producto(fechaCaducidad, numeroLote) {

    constructor(): this("SIN_FECHA_CAD", "SIN_LOTE", -18.0)

    override fun info(): String =
        "ProductoCongelado(fechaCaducidad=$fechaCaducidad, numeroLote=$numeroLote, temperaturaRecomendada=$temperaturaRecomendada°C)"
}
