package com.drk.pmdmud13cp01.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.drk.pmdmud13cp01.data.local.PeliculaFavoritaDao
import com.drk.pmdmud13cp01.data.remote.PelisApiService
import com.drk.pmdmud13cp01.model.Pelicula
import com.drk.pmdmud13cp01.model.PeliculaFavorita

class PeliculaRepository(
    private val apiService: PelisApiService,
    private val peliculaFavoritaDao: PeliculaFavoritaDao
) {
    // API Methods
    suspend fun obtenerPeliculasEnCartelera(apiKey: String): List<Pelicula>? {
        val response = apiService.getPeliculasEnCartelera(apiKey)
        return if (response.isSuccessful) response.body()?.results else null
    }

    suspend fun obtenerPeliculasPopulares(apiKey: String): List<Pelicula>? {
        val response = apiService.getPeliculasPopulares(apiKey)
        return if (response.isSuccessful) response.body()?.results else null
    }

    suspend fun obtenerPeliculasMejorValoradas(apiKey: String): List<Pelicula>? {
        val response = apiService.getPeliculasMejorValoradas(apiKey)
        return if (response.isSuccessful) response.body()?.results else null
    }

    suspend fun obtenerPeliculasProximamente(apiKey: String): List<Pelicula>? {
        val response = apiService.getPeliculasProximamente(apiKey)
        return if (response.isSuccessful) response.body()?.results else null
    }

    // Room Methods - Favoritas por usuario
    fun obtenerFavoritas(usuarioId: Int): LiveData<List<Pelicula>> {
        return peliculaFavoritaDao.getAllFavoritasByUsuario(usuarioId).map { list ->
            list.map { it.toPelicula() }
        }
    }

    suspend fun obtenerFavoritasList(usuarioId: Int): List<Pelicula> {
        return peliculaFavoritaDao.getAllFavoritasListByUsuario(usuarioId).map { it.toPelicula() }
    }

    suspend fun esFavorita(usuarioId: Int, peliculaId: Int): Boolean {
        return peliculaFavoritaDao.isFavorita(usuarioId, peliculaId)
    }

    suspend fun agregarFavorita(pelicula: Pelicula, usuarioId: Int) {
        val peliculaFavorita = PeliculaFavorita.fromPelicula(pelicula, usuarioId)
        peliculaFavoritaDao.insertFavorita(peliculaFavorita)
    }

    suspend fun eliminarFavorita(pelicula: Pelicula, usuarioId: Int) {
        peliculaFavoritaDao.deleteFavoritaByIds(usuarioId, pelicula.id)
    }

    suspend fun toggleFavorita(pelicula: Pelicula, usuarioId: Int): Boolean {
        return if (peliculaFavoritaDao.isFavorita(usuarioId, pelicula.id)) {
            peliculaFavoritaDao.deleteFavoritaByIds(usuarioId, pelicula.id)
            false
        } else {
            val peliculaFavorita = PeliculaFavorita.fromPelicula(pelicula, usuarioId)
            peliculaFavoritaDao.insertFavorita(peliculaFavorita)
            true
        }
    }
}