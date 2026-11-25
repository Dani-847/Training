package com.drk.pmdmud10cp01.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.drk.pmdmud10cp01.dao.PeliculaDAO
import com.drk.pmdmud10cp01.dao.UsuarioDao
import com.drk.pmdmud10cp01.model.Pelicula
import com.drk.pmdmud10cp01.model.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Pelicula::class, Usuario::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun peliculaDao(): PeliculaDAO
    abstract fun usuarioDao(): UsuarioDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "peliculas_database"
                )
                    .addCallback(DatabaseCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class DatabaseCallback : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // Insertar usuario admin por defecto
                db.execSQL(
                    "INSERT INTO usuarios (nombre, email, password) VALUES ('Administrador', 'admin@admin.com', '1234')"
                )
            }
        }
    }
}
