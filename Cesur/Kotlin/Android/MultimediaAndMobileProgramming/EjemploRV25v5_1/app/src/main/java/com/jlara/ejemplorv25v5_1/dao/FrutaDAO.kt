package com.jlara.ejemplorv25v5_1.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.jlara.ejemplorv25v5_1.contract.FrutaContract
import com.jlara.ejemplorv25v5_1.model.Fruta

class FrutaDAO {
    fun cargarLista(context: Context?): MutableList<Fruta> {
        lateinit var res:MutableList<Fruta>
        lateinit var c: Cursor
        try {
            // Obtener acceso de solo lectura
            val db = DBOpenHelper.getInstance(context)!!.readableDatabase
            // val sql = "SELECT * FROM frutas;"
            // c = db.rawQuery(sql, null)
            val columnas = arrayOf(
                FrutaContract.Companion.Entrada.IDCOL,
                FrutaContract.Companion.Entrada.NOMBRECOL,
                FrutaContract.Companion.Entrada.DESCRIPCIONCOL,
                FrutaContract.Companion.Entrada.IMAGENCOL)
            c = db.query(FrutaContract.Companion.Entrada.TABLA,
                columnas,null,null,null,null,null)
            res= mutableListOf()
            // Leer resultados del cursor e insertarlos en la lista
            while (c.moveToNext()) {
                val nueva = Fruta(c.getInt(0),c.getString(1),
                    c.getString(2),c.getInt(3))
                res.add(nueva)
            }
        } finally {
            c.close()
        }
        return res
    }

    fun actualizarBBDD(context: Context?, fruta: Fruta) {
        val db = DBOpenHelper.getInstance(context)!!.writableDatabase
        /*db.execSQL(
            "UPDATE frutas "
                    + "SET nombre='${fruta.nombre}' " +
                    "SET descripcion='${fruta.descripcion}'" +
                    "SET imagen='${fruta.imagen}'" +
                    "WHERE id=${fruta.id};"
        )
        */
        val values = ContentValues()
        values.put(FrutaContract.Companion.Entrada.IDCOL,fruta.id)
        values.put(FrutaContract.Companion.Entrada.NOMBRECOL,fruta.nombre)
        values.put(FrutaContract.Companion.Entrada.DESCRIPCIONCOL,fruta.descripcion)
        values.put(FrutaContract.Companion.Entrada.IMAGENCOL,fruta.imagen)
        db.update(FrutaContract.Companion.Entrada.TABLA,values,"id=?",arrayOf(fruta.id.toString()))
        db.close()
    }

    fun insertarBBDD(context: Context?, fruta:Fruta){
        val db = DBOpenHelper.getInstance(context)!!.writableDatabase
        db.execSQL(
            "INSERT INTO frutas (nombre, descripcion, imagen) VALUES "
                    + "('${fruta.nombre}', " +
                    "'${fruta.descripcion}', ${fruta.imagen});"
        )
        /*
        val values = ContentValues()
        values.put(FrutaContract.Companion.Entrada.COLUMNA_ID,fruta.id)
        values.put(FrutaContract.Companion.Entrada.COLUMNA_NOMBRE,fruta.nombre)
        values.put(FrutaContract.Companion.Entrada.COLUMNA_DESCRIPCION,fruta.descripcion)
        values.put(FrutaContract.Companion.Entrada.COLUMNA_IMAGEN,fruta.imagen)
        db.insert(FrutaContract.Companion.Entrada.NOMBRE_TABLA,null,values)*/
        db.close()
    }

    fun eliminar(context: Context?, fruta:Fruta){
        val db = DBOpenHelper.getInstance(context)!!.writableDatabase
        val sql = "DELETE FROM frutas WHERE id=${fruta.id};"
        db.execSQL(
            sql
        )

        /* val values = arrayOf((fruta.id).toString())
         db.delete(FrutaContract.Companion.Entrada.NOMBRE_TABLA,"id=?",values)*/
        db.close()
    }
}