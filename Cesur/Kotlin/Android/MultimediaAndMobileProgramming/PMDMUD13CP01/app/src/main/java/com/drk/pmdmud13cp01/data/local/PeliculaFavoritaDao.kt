package com.drk.pmdmud13cp01.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.drk.pmdmud13cp01.model.PeliculaFavorita

@Dao
interface PeliculaFavoritaDao {

    @Query("SELECT * FROM peliculas_favoritas_usuario WHERE usuarioId = :usuarioId ORDER BY title ASC")
    fun getAllFavoritasByUsuario(usuarioId: Int): LiveData<List<PeliculaFavorita>>

    @Query("SELECT * FROM peliculas_favoritas_usuario WHERE usuarioId = :usuarioId ORDER BY title ASC")
    suspend fun getAllFavoritasListByUsuario(usuarioId: Int): List<PeliculaFavorita>

    @Query("SELECT * FROM peliculas_favoritas_usuario WHERE usuarioId = :usuarioId AND peliculaId = :peliculaId LIMIT 1")
    suspend fun getFavoritaByUsuarioAndPelicula(usuarioId: Int, peliculaId: Int): PeliculaFavorita?

    @Query("SELECT EXISTS(SELECT 1 FROM peliculas_favoritas_usuario WHERE usuarioId = :usuarioId AND peliculaId = :peliculaId)")
    suspend fun isFavorita(usuarioId: Int, peliculaId: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorita(peliculaFavorita: PeliculaFavorita)

    @Delete
    suspend fun deleteFavorita(peliculaFavorita: PeliculaFavorita)

    @Query("DELETE FROM peliculas_favoritas_usuario WHERE usuarioId = :usuarioId AND peliculaId = :peliculaId")
    suspend fun deleteFavoritaByIds(usuarioId: Int, peliculaId: Int)
}
