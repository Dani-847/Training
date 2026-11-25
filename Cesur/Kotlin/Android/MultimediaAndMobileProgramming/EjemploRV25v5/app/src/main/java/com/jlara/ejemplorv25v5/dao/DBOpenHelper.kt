package com.jlara.ejemplorv25v5.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.jlara.ejemplorv25v5.contract.FrutaContract
import com.jlara.ejemplorv25v5.provider.FrutaProvider
import java.lang.Exception

class DBOpenHelper private constructor(context: Context?) :
    SQLiteOpenHelper(context, FrutaContract.NOMBRE_BD, null, FrutaContract.VERSION) {

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(
                "CREATE TABLE ${FrutaContract.Companion.Entrada.TABLA}"
                        +"(${FrutaContract.Companion.Entrada.IDCOL} integer primary key"
                        + ",${FrutaContract.Companion.Entrada.NOMBRECOL} NVARCHAR(20) NOT NULL"
                        + ",${FrutaContract.Companion.Entrada.DESCRIPCIONCOL} NVARCHAR(40) NOT NULL"
                        + ",${FrutaContract.Companion.Entrada.IMAGENCOL} int NOT NULL);"
            )
            // Insertar datos en la tabla
            inicializarBBDD(sqLiteDatabase)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ${FrutaContract.Companion.Entrada.TABLA};")
        onCreate(sqLiteDatabase)
    }

    private fun inicializarBBDD(db: SQLiteDatabase) {
        val lista = FrutaProvider.cargarLista()
        for (fruta in lista) {
            db.execSQL(
                ("INSERT INTO ${FrutaContract.Companion.Entrada.TABLA}("+
                        "${FrutaContract.Companion.Entrada.NOMBRECOL},"+
                        "${FrutaContract.Companion.Entrada.DESCRIPCIONCOL},"+
                        "${FrutaContract.Companion.Entrada.IMAGENCOL})"+
                        " VALUES ('${fruta.nombre}','${fruta.descripcion}',${fruta.imagen});")
            )
        }
    }


    companion object {
        private var dbOpen: DBOpenHelper? = null
        fun getInstance(context: Context?): DBOpenHelper? {
            if (dbOpen == null) dbOpen = DBOpenHelper(context)
            return dbOpen
        }
    }
}

