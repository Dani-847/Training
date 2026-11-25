package com.example.proyecto26_09_25.Ej3

class ProductoRefrigerado(
    fechaCaducidad: String,
    numeroLote: String,
    var codigoSupervision: String
) : Producto(fechaCaducidad, numeroLote) {

    constructor(): this("SIN_FECHA_CAD", "SIN_LOTE", "SIN_CODIGO")

    override fun info(): String =
        "ProductoRefrigerado(fechaCaducidad=$fechaCaducidad, numeroLote=$numeroLote, codigoSupervision=$codigoSupervision)"
}
