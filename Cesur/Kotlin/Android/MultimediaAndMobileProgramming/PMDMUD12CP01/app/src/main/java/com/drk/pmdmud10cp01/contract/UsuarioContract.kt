package com.drk.pmdmud10cp01.contract

import android.provider.BaseColumns

class UsuarioContract {
    companion object {
        class Entrada : BaseColumns {
            companion object {
                const val TABLA = "usuarios"
                const val IDCOL = "id"
                const val NOMBRECOL = "nombre"
                const val EMAILCOL = "email"
                const val PASSWORDCOL = "password"
            }
        }
    }
}
