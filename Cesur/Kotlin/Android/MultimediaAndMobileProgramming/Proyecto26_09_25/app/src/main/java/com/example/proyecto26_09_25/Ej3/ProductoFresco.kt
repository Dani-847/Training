package com.example.proyecto26_09_25.Ej3

class ProductoFresco(
    fechaCaducidad: String,
    numeroLote: String,
    var fechaEnvasado: String,
    var paisOrigen: String
) : Producto(fechaCaducidad, numeroLote) {

    constructor(): this("SIN_FECHA_CAD", "SIN_LOTE", "SIN_ENVASADO", "SIN_PAIS")

    override fun info(): String =
        "ProductoFresco(fechaCaducidad=$fechaCaducidad, numeroLote=$numeroLote, fechaEnvasado=$fechaEnvasado, paisOrigen=$paisOrigen)"
}
