package com.drk.pmdmud13cp01.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drk.pmdmud13cp01.model.CategoriaPelicula
import com.drk.pmdmud13cp01.model.Pelicula
import com.drk.pmdmud13cp01.data.repository.PeliculaRepository
import kotlinx.coroutines.launch

class PeliculaViewModel(
    private val repository: PeliculaRepository,
    private val usuarioId: Int
) : ViewModel() {

    companion object {
        const val API_KEY = "" // [CREDENTIAL REMOVED FOR SECURITY] A TMDB API key used to be here. Third parties: it is not available. Owner: retrieve it or create a new one at https://www.themoviedb.org/settings/api (never commit it to a public repository).
    }

    private val _peliculas = MutableLiveData<List<Pelicula>>()
    val peliculas: LiveData<List<Pelicula>> = _peliculas

    private val _categoriaActual = MutableLiveData<CategoriaPelicula>()
    val categoriaActual: LiveData<CategoriaPelicula> = _categoriaActual

    private val _esFavorita = MutableLiveData<Boolean>()
    val esFavorita: LiveData<Boolean> = _esFavorita

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // Evento para mostrar notificación de favorita
    private val _favoritaEvent = MutableLiveData<Boolean?>()
    val favoritaEvent: LiveData<Boolean?> = _favoritaEvent

    val peliculasFavoritas: LiveData<List<Pelicula>> = repository.obtenerFavoritas(usuarioId)

    init {
        _categoriaActual.value = CategoriaPelicula.EN_CARTELERA
        cargarPeliculas(CategoriaPelicula.EN_CARTELERA)
    }

    fun cargarPeliculas(categoria: CategoriaPelicula) {
        _categoriaActual.value = categoria
        _isLoading.value = true

        viewModelScope.launch {
            val resultado = when (categoria) {
                CategoriaPelicula.EN_CARTELERA -> repository.obtenerPeliculasEnCartelera(API_KEY)
                CategoriaPelicula.POPULARES -> repository.obtenerPeliculasPopulares(API_KEY)
                CategoriaPelicula.MEJOR_VALORADAS -> repository.obtenerPeliculasMejorValoradas(API_KEY)
                CategoriaPelicula.PROXIMAMENTE -> repository.obtenerPeliculasProximamente(API_KEY)
                CategoriaPelicula.FAVORITAS -> repository.obtenerFavoritasList(usuarioId)
            }
            _peliculas.value = resultado ?: emptyList()
            _isLoading.value = false
        }
    }

    fun verificarSiEsFavorita(peliculaId: Int) {
        viewModelScope.launch {
            _esFavorita.value = repository.esFavorita(usuarioId, peliculaId)
        }
    }

    fun toggleFavorita(pelicula: Pelicula) {
        viewModelScope.launch {
            val esAhoraFavorita = repository.toggleFavorita(pelicula, usuarioId)
            _esFavorita.value = esAhoraFavorita
            _favoritaEvent.value = esAhoraFavorita
        }
    }

    fun clearFavoritaEvent() {
        _favoritaEvent.value = null
    }

    fun agregarFavorita(pelicula: Pelicula) {
        viewModelScope.launch {
            repository.agregarFavorita(pelicula, usuarioId)
            _esFavorita.value = true
            _favoritaEvent.value = true
        }
    }

    fun eliminarFavorita(pelicula: Pelicula) {
        viewModelScope.launch {
            repository.eliminarFavorita(pelicula, usuarioId)
            _esFavorita.value = false
            _favoritaEvent.value = false
        }
    }
}

