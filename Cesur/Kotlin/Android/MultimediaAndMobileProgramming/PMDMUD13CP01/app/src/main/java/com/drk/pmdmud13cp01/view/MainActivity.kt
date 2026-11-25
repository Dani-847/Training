package com.drk.pmdmud13cp01.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.drk.pmdmud13cp01.model.CategoriaPelicula
import com.drk.pmdmud13cp01.model.Pelicula
import com.drk.pmdmud13cp01.adapter.PeliculaAdapter
import com.drk.pmdmud13cp01.data.local.PeliculaDatabase
import com.drk.pmdmud13cp01.data.local.SessionManager
import com.drk.pmdmud13cp01.data.repository.PeliculaRepository
import com.drk.pmdmud13cp01.data.remote.RetrofitClient
import com.drk.pmdmud13cp01.R
import com.drk.pmdmud13cp01.viewmodel.PeliculaViewModel
import com.drk.pmdmud13cp01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PeliculaAdapter
    private lateinit var sessionManager: SessionManager
    private lateinit var viewModel: PeliculaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        sessionManager = SessionManager(this)

        // Verificar si está logueado
        if (!sessionManager.isLoggedIn()) {
            goToLogin()
            return
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar ViewModel con userId
        val database = PeliculaDatabase.getDatabase(applicationContext)
        val factory = PeliculaViewModelFactory(
            PeliculaRepository(RetrofitClient.apiService, database.peliculaFavoritaDao()),
            sessionManager.getUserId()
        )
        viewModel = ViewModelProvider(this, factory)[PeliculaViewModel::class.java]

        // Configurar Toolbar
        setSupportActionBar(binding.toolbar)

        // Configurar RecyclerView
        binding.rvPeliculas.layoutManager = LinearLayoutManager(this)
        adapter = PeliculaAdapter(mutableListOf()) { pelicula ->
            onItemSelected(pelicula)
        }
        binding.rvPeliculas.adapter = adapter

        // Observar cambios en las películas
        viewModel.peliculas.observe(this) { peliculas ->
            handleEmptyView(peliculas)
            adapter.updateData(peliculas)
        }

        // Observar cambios en la categoría actual
        viewModel.categoriaActual.observe(this) { categoria ->
            supportActionBar?.title = categoria.titulo
        }

        // Observar favoritas para refrescar cuando cambie
        viewModel.peliculasFavoritas.observe(this) { _ ->
            // Si estamos viendo favoritas, refrescamos
            if (viewModel.categoriaActual.value == CategoriaPelicula.FAVORITAS) {
                viewModel.cargarPeliculas(CategoriaPelicula.FAVORITAS)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Refrescar si estamos viendo favoritas (por si se añadió/quitó desde detalle)
        if (::viewModel.isInitialized && viewModel.categoriaActual.value == CategoriaPelicula.FAVORITAS) {
            viewModel.cargarPeliculas(CategoriaPelicula.FAVORITAS)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_categorias, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_favoritas -> {
                viewModel.cargarPeliculas(CategoriaPelicula.FAVORITAS)
                true
            }
            R.id.menu_en_cartelera -> {
                viewModel.cargarPeliculas(CategoriaPelicula.EN_CARTELERA)
                true
            }
            R.id.menu_populares -> {
                viewModel.cargarPeliculas(CategoriaPelicula.POPULARES)
                true
            }
            R.id.menu_mejor_valoradas -> {
                viewModel.cargarPeliculas(CategoriaPelicula.MEJOR_VALORADAS)
                true
            }
            R.id.menu_proximamente -> {
                viewModel.cargarPeliculas(CategoriaPelicula.PROXIMAMENTE)
                true
            }
            R.id.menu_logout -> {
                logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun logout() {
        sessionManager.logout()
        goToLogin()
    }

    private fun goToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun onItemSelected(pelicula: Pelicula) {
        val intent = Intent(this, DetalleActivity::class.java)
        intent.putExtra("pelicula", pelicula)
        startActivity(intent)
    }

    private fun handleEmptyView(pelisList: List<Pelicula>) {
        if (pelisList.isEmpty()) {
            binding.tvEmpty.visibility = View.VISIBLE
            binding.rvPeliculas.visibility = View.GONE
        } else {
            binding.tvEmpty.visibility = View.GONE
            binding.rvPeliculas.visibility = View.VISIBLE
        }
    }
}

// Factory para el ViewModel
class PeliculaViewModelFactory(
    private val repository: PeliculaRepository,
    private val usuarioId: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PeliculaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PeliculaViewModel(repository, usuarioId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

