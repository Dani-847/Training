package com.drk.pmdmud10cp01.dao

import androidx.room.*
import com.drk.pmdmud10cp01.model.Pelicula
import kotlinx.coroutines.flow.Flow

@Dao
interface PeliculaDAO {
    @Query("SELECT * FROM peliculas ORDER BY titulo ASC")
    fun getAllPeliculas(): Flow<List<Pelicula>>

    @Query("SELECT * FROM peliculas WHERE id = :id")
    suspend fun getPeliculaById(id: Int): Pelicula?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPelicula(pelicula: Pelicula)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(peliculas: List<Pelicula>)

    @Update
    suspend fun updatePelicula(pelicula: Pelicula)

    @Delete
    suspend fun deletePelicula(pelicula: Pelicula)

    @Query("UPDATE peliculas SET fotoPath = :fotoPath WHERE id = :id")
    suspend fun updateFotoPath(id: Int, fotoPath: String)
}
