package com.drk.examen21_11_2025.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.drk.examen21_11_2025.contract.ProductoContract
import com.drk.examen21_11_2025.provider.ProductoProvider

class DBOpenHelper private constructor(context: Context?) : SQLiteOpenHelper(context,
    ProductoContract.Companion.NOMBRE_DB, null, ProductoContract.Companion.VERSION
) {
    override fun onCreate(sQLiteDatabase: SQLiteDatabase?) {
        sQLiteDatabase?.execSQL(
            "CREATE TABLE ${ProductoContract.Companion.Entrada.TABLA}" +
                    "(${ProductoContract.Companion.Entrada.ID} integer PRIMARY KEY" +
                    ",${ProductoContract.Companion.Entrada.NOMBRE} NVARCHAR(50) NOT NULL" +
                    ",${ProductoContract.Companion.Entrada.IMAGEN} int NOT NULL" +
                    ",${ProductoContract.Companion.Entrada.PRECIO} double NOT NULL" +
                    ",${ProductoContract.Companion.Entrada.CATEGORIA} NVARCHAR(50) NOT NULL" +
                    ",${ProductoContract.Companion.Entrada.DESCRIPCION} NVARCHAR(50) NOT NULL);"
        )
        inicializarBBDD(sQLiteDatabase)
    }


    override fun onUpgrade(
        p0: SQLiteDatabase?,
        p1: Int,
        p2: Int
    ) {
        p0?.execSQL("DROP TABLE IF EXISTS ${ProductoContract.Companion.Entrada.TABLA};")
        onCreate(p0)
    }

    private fun inicializarBBDD(db: SQLiteDatabase?) {
        val lista = ProductoProvider.listaCarga()
        for (producto in lista) {
            db?.execSQL(
                "INSERT INTO ${ProductoContract.Companion.Entrada.TABLA}(" +
                        "${ProductoContract.Companion.Entrada.NOMBRE}," +
                        "${ProductoContract.Companion.Entrada.IMAGEN}," +
                        "${ProductoContract.Companion.Entrada.PRECIO}," +
                        "${ProductoContract.Companion.Entrada.CATEGORIA}," +
                        "${ProductoContract.Companion.Entrada.DESCRIPCION}" +
                        ") VALUES (" +
                        "'${producto.nombre}'," +
                        "${producto.imagen}," +
                        "${producto.precio}," +
                        "'${producto.categoria}'," +
                        "'${producto.descripcion}'" +
                        ");"
            )
        }
    }
    companion object {
        private var dbOpenHelper: DBOpenHelper? = null
        fun getInstance(context: Context?): DBOpenHelper? {
            if (dbOpenHelper == null) {
                dbOpenHelper = DBOpenHelper(context)
            }
            return dbOpenHelper
        }
    }
}