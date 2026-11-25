package com.drk.ud09cp01

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context

class PeliculaDAO(context: Context) {

    private val dbHelper = DBOpenHelper(context)

    fun insertarPeliculasIniciales() {
        if (isVacia()) {
            val db = dbHelper.writableDatabase
            PeliculaProvider.listaCarga.forEach { pelicula ->
                val values = ContentValues().apply {
                    put(PeliculaContract.Companion.Entradas.COLUMN_ID, pelicula.id)
                    put(PeliculaContract.Companion.Entradas.COLUMN_TITULO, pelicula.titulo)
                    put(PeliculaContract.Companion.Entradas.COLUMN_DESCRIPCION, pelicula.descripcion)
                    put(PeliculaContract.Companion.Entradas.COLUMN_IMG, pelicula.img)
                    put(PeliculaContract.Companion.Entradas.COLUMN_MINUTOS, pelicula.minutos)
                    put(PeliculaContract.Companion.Entradas.COLUMN_ANHO, pelicula.anho)
                    put(PeliculaContract.Companion.Entradas.COLUMN_PAIS, pelicula.pais)
                }
                db.insert(PeliculaContract.Companion.Entradas.TABLE_NAME, null, values)
            }
            db.close()
        }
    }

    @SuppressLint("Range")
    fun getAllPeliculas(): MutableList<Pelicula> {
        val peliculas = mutableListOf<Pelicula>()
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ${PeliculaContract.Companion.Entradas.TABLE_NAME}", null)

        if (cursor.moveToFirst()) {
            do {
                peliculas.add(
                    Pelicula(
                        id = cursor.getInt(cursor.getColumnIndex(PeliculaContract.Companion.Entradas.COLUMN_ID)),
                        titulo = cursor.getString(cursor.getColumnIndex(PeliculaContract.Companion.Entradas.COLUMN_TITULO)),
                        descripcion = cursor.getString(cursor.getColumnIndex(PeliculaContract.Companion.Entradas.COLUMN_DESCRIPCION)),
                        img = cursor.getInt(cursor.getColumnIndex(PeliculaContract.Companion.Entradas.COLUMN_IMG)),
                        minutos = cursor.getInt(cursor.getColumnIndex(PeliculaContract.Companion.Entradas.COLUMN_MINUTOS)),
                        anho = cursor.getInt(cursor.getColumnIndex(PeliculaContract.Companion.Entradas.COLUMN_ANHO)),
                        pais = cursor.getString(cursor.getColumnIndex(PeliculaContract.Companion.Entradas.COLUMN_PAIS))
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return peliculas
    }

    fun editarPelicula(pelicula: Pelicula, nuevoTitulo: String) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(PeliculaContract.Companion.Entradas.COLUMN_TITULO, nuevoTitulo)
        }
        db.update(PeliculaContract.Companion.Entradas.TABLE_NAME, values, "${PeliculaContract.Companion.Entradas.COLUMN_ID} = ?", arrayOf(pelicula.id.toString()))
        db.close()
    }

    fun eliminarPelicula(pelicula: Pelicula) {
        val db = dbHelper.writableDatabase
        db.delete(PeliculaContract.Companion.Entradas.TABLE_NAME, "${PeliculaContract.Companion.Entradas.COLUMN_ID} = ?", arrayOf(pelicula.id.toString()))
        db.close()
    }

    fun limpiarPeliculas() {
        val db = dbHelper.writableDatabase
        db.delete(PeliculaContract.Companion.Entradas.TABLE_NAME, null, null)
        db.close()
    }

    @SuppressLint("Range")
    fun buscarPeliculas(query: String): MutableList<Pelicula> {
        val peliculas = mutableListOf<Pelicula>()
        val db = dbHelper.readableDatabase
        val selection = "${PeliculaContract.Companion.Entradas.COLUMN_TITULO} LIKE ?"
        val selectionArgs = arrayOf("%$query%")
        val cursor = db.query(
            PeliculaContract.Companion.Entradas.TABLE_NAME,
            null, 
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        if (cursor.moveToFirst()) {
            do {
                peliculas.add(
                    Pelicula(
                        id = cursor.getInt(cursor.getColumnIndex(PeliculaContract.Companion.Entradas.COLUMN_ID)),
                        titulo = cursor.getString(cursor.getColumnIndex(PeliculaContract.Companion.Entradas.COLUMN_TITULO)),
                        descripcion = cursor.getString(cursor.getColumnIndex(PeliculaContract.Companion.Entradas.COLUMN_DESCRIPCION)),
                        img = cursor.getInt(cursor.getColumnIndex(PeliculaContract.Companion.Entradas.COLUMN_IMG)),
                        minutos = cursor.getInt(cursor.getColumnIndex(PeliculaContract.Companion.Entradas.COLUMN_MINUTOS)),
                        anho = cursor.getInt(cursor.getColumnIndex(PeliculaContract.Companion.Entradas.COLUMN_ANHO)),
                        pais = cursor.getString(cursor.getColumnIndex(PeliculaContract.Companion.Entradas.COLUMN_PAIS))
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return peliculas
    }

    private fun isVacia(): Boolean {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM ${PeliculaContract.Companion.Entradas.TABLE_NAME}", null)
        cursor.moveToFirst()
        val count = cursor.getInt(0)
        cursor.close()
        db.close()
        return count == 0
    }
}
