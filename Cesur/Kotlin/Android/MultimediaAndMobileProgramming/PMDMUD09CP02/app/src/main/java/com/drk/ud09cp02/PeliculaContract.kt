package com.drk.ud09cp02

import android.provider.BaseColumns

class PeliculaContract {
    companion object{
        const val NOMBRE_BD = "peliculas.db"
        const val VERSION = 1
        class Entradas : BaseColumns {
            companion object{
                const val TABLE_NAME = "peliculas"
                const val COLUMN_ID = "id"
                const val COLUMN_TITULO = "titulo"
                const val COLUMN_DESCRIPCION = "descripcion"
                const val COLUMN_IMG = "img"
                const val COLUMN_MINUTOS = "minutos"
                const val COLUMN_ANHO = "anho"
                const val COLUMN_PAIS = "pais"
            }
        }
    }
}
