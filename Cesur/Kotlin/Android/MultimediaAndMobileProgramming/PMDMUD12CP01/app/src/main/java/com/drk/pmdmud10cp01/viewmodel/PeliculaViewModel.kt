package com.drk.pmdmud10cp01.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.drk.pmdmud10cp01.database.AppDatabase
import com.drk.pmdmud10cp01.model.Pelicula
import com.drk.pmdmud10cp01.provider.PeliculaProvider
import com.drk.pmdmud10cp01.repository.PeliculaRepository
import kotlinx.coroutines.launch

class PeliculaViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PeliculaRepository
    val allPeliculas: LiveData<List<Pelicula>>

    init {
        val peliculaDao = AppDatabase.getDatabase(application).peliculaDao()
        repository = PeliculaRepository(peliculaDao)
        allPeliculas = repository.allPeliculas.asLiveData()
    }

    fun insert(pelicula: Pelicula) = viewModelScope.launch {
        repository.insert(pelicula)
    }

    fun update(pelicula: Pelicula) = viewModelScope.launch {
        repository.update(pelicula)
    }

    fun delete(pelicula: Pelicula) = viewModelScope.launch {
        repository.delete(pelicula)
    }

    fun updateFotoPath(id: Int, fotoPath: String) = viewModelScope.launch {
        repository.updateFotoPath(id, fotoPath)
    }

    fun cargarDatosIniciales() = viewModelScope.launch {
        repository.insertAll(PeliculaProvider.listaPeliculas)
    }
}
