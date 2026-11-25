package com.drk.pmdmud10cp01.repository

import com.drk.pmdmud10cp01.dao.PeliculaDAO
import com.drk.pmdmud10cp01.model.Pelicula
import kotlinx.coroutines.flow.Flow

class PeliculaRepository(private val peliculaDAO: PeliculaDAO) {

    val allPeliculas: Flow<List<Pelicula>> = peliculaDAO.getAllPeliculas()

    suspend fun insert(pelicula: Pelicula) {
        peliculaDAO.insertPelicula(pelicula)
    }

    suspend fun insertAll(peliculas: List<Pelicula>) {
        peliculaDAO.insertAll(peliculas)
    }

    suspend fun update(pelicula: Pelicula) {
        peliculaDAO.updatePelicula(pelicula)
    }

    suspend fun delete(pelicula: Pelicula) {
        peliculaDAO.deletePelicula(pelicula)
    }

    suspend fun getPeliculaById(id: Int): Pelicula? {
        return peliculaDAO.getPeliculaById(id)
    }

    suspend fun updateFotoPath(id: Int, fotoPath: String) {
        peliculaDAO.updateFotoPath(id, fotoPath)
    }
}
