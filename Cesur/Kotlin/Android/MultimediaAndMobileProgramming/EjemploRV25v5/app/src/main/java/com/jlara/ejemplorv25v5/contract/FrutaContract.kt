package com.jlara.ejemplorv25v5.contract

import android.provider.BaseColumns

class FrutaContract {
    companion object{
        const val NOMBRE_BD = "dbfrutas"
        const val VERSION = 1
        class Entrada:BaseColumns{
            companion object{
                const val TABLA = "frutas"
                const val IDCOL = "id"
                const val NOMBRECOL = "nombre"
                const val DESCRIPCIONCOL = "descripcion"
                const val IMAGENCOL = "imagen"
            }
        }
    }
}
