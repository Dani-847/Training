package com.drk.mistareaspro.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TareasDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "tareas.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_TAREAS = "tareas"
        private const val COL_ID = "id"
        private const val COL_TITULO = "titulo"
        private const val COL_DESCRIPCION = "descripcion"
        private const val COL_FECHA = "fecha"
        private const val COL_PRIORIDAD = "prioridad"
        private const val COL_COMPLETADA = "completada"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_TAREAS (
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_TITULO TEXT NOT NULL,
                $COL_DESCRIPCION TEXT,
                $COL_FECHA TEXT,
                $COL_PRIORIDAD INTEGER,
                $COL_COMPLETADA INTEGER DEFAULT 0
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_TAREAS")
        onCreate(db)
    }

    fun insertTarea(tarea: Tarea): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_TITULO, tarea.titulo)
            put(COL_DESCRIPCION, tarea.descripcion)
            put(COL_FECHA, tarea.fecha)
            put(COL_PRIORIDAD, tarea.prioridad)
            put(COL_COMPLETADA, if (tarea.completada) 1 else 0)
        }
        return db.insert(TABLE_TAREAS, null, values)
    }

    fun getAllTareas(): List<Tarea> {
        val tareas = mutableListOf<Tarea>()
        val db = readableDatabase
        val cursor = db.query(TABLE_TAREAS, null, null, null, null, null, "$COL_FECHA DESC")
        with(cursor) {
            while (moveToNext()) {
                tareas.add(Tarea(
                    id = getLong(getColumnIndexOrThrow(COL_ID)),
                    titulo = getString(getColumnIndexOrThrow(COL_TITULO)),
                    descripcion = getString(getColumnIndexOrThrow(COL_DESCRIPCION)),
                    fecha = getString(getColumnIndexOrThrow(COL_FECHA)),
                    prioridad = getInt(getColumnIndexOrThrow(COL_PRIORIDAD)),
                    completada = getInt(getColumnIndexOrThrow(COL_COMPLETADA)) == 1
                ))
            }
        }
        cursor.close()
        return tareas
    }

    fun updateTarea(tarea: Tarea): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_TITULO, tarea.titulo)
            put(COL_DESCRIPCION, tarea.descripcion)
            put(COL_FECHA, tarea.fecha)
            put(COL_PRIORIDAD, tarea.prioridad)
            put(COL_COMPLETADA, if (tarea.completada) 1 else 0)
        }
        return db.update(TABLE_TAREAS, values, "$COL_ID = ?", arrayOf(tarea.id.toString()))
    }

    fun deleteTarea(id: Long): Int {
        val db = writableDatabase
        return db.delete(TABLE_TAREAS, "$COL_ID = ?", arrayOf(id.toString()))
    }

    fun getTareaById(id: Long): Tarea? {
        val db = readableDatabase
        val cursor = db.query(TABLE_TAREAS, null, "$COL_ID = ?", arrayOf(id.toString()), null, null, null)
        var tarea: Tarea? = null
        if (cursor.moveToFirst()) {
            tarea = Tarea(
                id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID)),
                titulo = cursor.getString(cursor.getColumnIndexOrThrow(COL_TITULO)),
                descripcion = cursor.getString(cursor.getColumnIndexOrThrow(COL_DESCRIPCION)),
                fecha = cursor.getString(cursor.getColumnIndexOrThrow(COL_FECHA)),
                prioridad = cursor.getInt(cursor.getColumnIndexOrThrow(COL_PRIORIDAD)),
                completada = cursor.getInt(cursor.getColumnIndexOrThrow(COL_COMPLETADA)) == 1
            )
        }
        cursor.close()
        return tarea
    }
}
