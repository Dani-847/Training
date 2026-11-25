package com.drk.pmdmud13cp01.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.drk.pmdmud13cp01.model.Pelicula
import com.drk.pmdmud13cp01.R
import com.drk.pmdmud13cp01.data.local.PeliculaDatabase
import com.drk.pmdmud13cp01.data.local.SessionManager
import com.drk.pmdmud13cp01.data.remote.RetrofitClient
import com.drk.pmdmud13cp01.data.repository.PeliculaRepository
import com.drk.pmdmud13cp01.databinding.ActivityDetalleBinding
import com.drk.pmdmud13cp01.viewmodel.PeliculaViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class DetalleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalleBinding
    private var pelicula: Pelicula? = null
    private lateinit var sessionManager: SessionManager
    private lateinit var viewModel: PeliculaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sessionManager = SessionManager(this)

        // Inicializar ViewModel con userId
        val database = PeliculaDatabase.getDatabase(applicationContext)
        val factory = PeliculaViewModelFactory(
            PeliculaRepository(RetrofitClient.apiService, database.peliculaFavoritaDao()),
            sessionManager.getUserId()
        )
        viewModel = ViewModelProvider(this, factory)[PeliculaViewModel::class.java]

        pelicula = intent.getParcelableExtra("pelicula", Pelicula::class.java)

        pelicula?.let { peli ->
            // Establecer información de la película
            binding.tvTitulo.text = peli.title
            binding.tvTituloOriginal.text = peli.originalTitle ?: peli.title
            binding.tvOverview.text = peli.overview ?: getString(R.string.nohay)

            // Fecha de estreno
            binding.tvEstreno.text = getString(R.string.estreno, peli.releaseDate ?: "N/A")

            // Valoración
            binding.tvValoracion.text = getString(R.string.valoracion_del_publico, peli.voteAverage?.toString() ?: "N/A")

            // Cargar imagen de la película
            val imageUrl = "https://image.tmdb.org/t/p/w780${peli.backdropPath}"
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.imagen_placeholder)
                .error(R.drawable.noposterp)
                .into(binding.ivPelicula)

            // Verificar si es favorita
            viewModel.verificarSiEsFavorita(peli.id)

            // Observar cambios en el estado de favorita
            viewModel.esFavorita.observe(this) { esFavorita ->
                actualizarEstadoFavorito(esFavorita)
            }

            // Observar evento de favorita para mostrar Snackbar
            viewModel.favoritaEvent.observe(this) { esAhoraFavorita ->
                esAhoraFavorita?.let { agregada ->
                    val mensaje = if (agregada) {
                        getString(R.string.agregada_favoritas)
                    } else {
                        getString(R.string.quitada_favoritas)
                    }
                    Snackbar.make(binding.main, mensaje, Snackbar.LENGTH_SHORT).show()
                    viewModel.clearFavoritaEvent()
                }
            }

            // Click en el botón de estrella (icono)
            binding.btnFavoritos.setOnClickListener {
                viewModel.toggleFavorita(peli)
            }
        }
    }

    private fun actualizarEstadoFavorito(esFavorita: Boolean) {
        if (esFavorita) {
            binding.btnFavoritos.setImageResource(R.drawable.ic_star_filled)
        } else {
            binding.btnFavoritos.setImageResource(R.drawable.ic_star_outline)
        }
    }
}

