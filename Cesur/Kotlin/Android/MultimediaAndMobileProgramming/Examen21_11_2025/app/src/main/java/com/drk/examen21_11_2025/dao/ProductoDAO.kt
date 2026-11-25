package com.drk.examen21_11_2025.dao

import android.content.ContentValues
import android.content.Context
import com.drk.examen21_11_2025.contract.ProductoContract
import com.drk.examen21_11_2025.model.Producto
import com.drk.examen21_11_2025.provider.ProductoProvider

public class ProductoDAO (){

    fun actualizarBBDD(context: Context, producto: Producto) {
        val db = DBOpenHelper.getInstance(context)?.writableDatabase ?: return

        val values = ContentValues().apply {
            put(ProductoContract.Companion.Entrada.NOMBRE, producto.nombre)
            put(ProductoContract.Companion.Entrada.IMAGEN, producto.imagen)
            put(ProductoContract.Companion.Entrada.PRECIO, producto.precio)
            put(ProductoContract.Companion.Entrada.CATEGORIA, producto.categoria)
            put(ProductoContract.Companion.Entrada.DESCRIPCION, producto.descripcion)
        }

        db.update(
            ProductoContract.Companion.Entrada.TABLA,
            values,
            "${ProductoContract.Companion.Entrada.ID} = ?",
            arrayOf(producto.id.toString())
        )
    }
    fun cargarLista(context: Context?): List<Producto>{
        val db= DBOpenHelper.Companion.getInstance(context)?.readableDatabase

        val cursor= db?.rawQuery("SELECT * FROM producto",null)
        val lista= mutableListOf<Producto>()

        while (cursor!!.moveToNext()){
            val producto= Producto(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getInt(2),
                cursor.getDouble(3),
                cursor.getString(4),
                cursor.getString(5),
            )
            lista.add(producto)
        }
        db.close()
        return lista

    }

    fun cargarListaOriginal(context: Context?):List<Producto>{
        borrarProductos(context)
        for (producto in ProductoProvider.Companion.listaCarga()) {
            insertarBBDD(context,producto)
        }
        return cargarLista(context)
    }
    fun borrarProductos(context: Context?){
        val db= DBOpenHelper.Companion.getInstance(context)?.writableDatabase
        db?.execSQL("DELETE FROM producto")
        db?.close()
    }
    fun eliminar(context: Context?, producto: Producto){
        val db= DBOpenHelper.Companion.getInstance(context)?.writableDatabase
        db?.execSQL("DELETE FROM producto WHERE id=${producto.id}")
        db?.close()
    }
    fun insertarBBDD(context: Context?, producto: Producto){
        val db= DBOpenHelper.Companion.getInstance(context)?.writableDatabase
        db?.execSQL("INSERT INTO producto(imagen,nombre,precio,categoria,descripcion) VALUES ('${producto.imagen}','${producto.nombre}','${producto.precio}','${producto.categoria}','${producto.descripcion}');")
        db?.close()
    }
}