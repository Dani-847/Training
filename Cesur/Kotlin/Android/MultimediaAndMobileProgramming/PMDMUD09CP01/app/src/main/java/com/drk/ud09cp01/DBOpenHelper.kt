package com.drk.ud09cp01

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBOpenHelper(context: Context) : SQLiteOpenHelper(context, PeliculaContract.NOMBRE_BD, null, PeliculaContract.VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = """CREATE TABLE ${PeliculaContract.Companion.Entradas.TABLE_NAME} (
                ${PeliculaContract.Companion.Entradas.COLUMN_ID} INTEGER PRIMARY KEY,
                ${PeliculaContract.Companion.Entradas.COLUMN_TITULO} TEXT,
                ${PeliculaContract.Companion.Entradas.COLUMN_DESCRIPCION} TEXT,
                ${PeliculaContract.Companion.Entradas.COLUMN_IMG} INTEGER,
                ${PeliculaContract.Companion.Entradas.COLUMN_MINUTOS} INTEGER,
                ${PeliculaContract.Companion.Entradas.COLUMN_ANHO} INTEGER,
                ${PeliculaContract.Companion.Entradas.COLUMN_PAIS} TEXT
        )"""
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${PeliculaContract.Companion.Entradas.TABLE_NAME}")
        onCreate(db)
    }
}
