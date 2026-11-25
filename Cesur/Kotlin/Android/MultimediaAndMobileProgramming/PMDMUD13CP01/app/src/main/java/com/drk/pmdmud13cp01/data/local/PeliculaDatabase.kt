package com.drk.pmdmud13cp01.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.drk.pmdmud13cp01.model.PeliculaFavorita
import com.drk.pmdmud13cp01.model.Usuario

@Database(
    entities = [Usuario::class, PeliculaFavorita::class],
    version = 2,
    exportSchema = false
)
abstract class PeliculaDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun peliculaFavoritaDao(): PeliculaFavoritaDao

    companion object {
        @Volatile
        private var INSTANCE: PeliculaDatabase? = null

        fun getDatabase(context: Context): PeliculaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PeliculaDatabase::class.java,
                    "peliculas_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
