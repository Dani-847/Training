package com.drk.examen21_11_2025.contract

import android.provider.BaseColumns

class ProductoContract {
    companion object{
        const val NOMBRE_DB = "producto"
        const val VERSION = 1
        class Entrada: BaseColumns {
            companion object {
                const val TABLA = "producto"
                val ID = "id"
                var NOMBRE = "nombre"
                val IMAGEN = "imagen"
                val PRECIO = "precio"
                val CATEGORIA = "categoria"
                val DESCRIPCION = "descripcion"
            }
        }
    }
}