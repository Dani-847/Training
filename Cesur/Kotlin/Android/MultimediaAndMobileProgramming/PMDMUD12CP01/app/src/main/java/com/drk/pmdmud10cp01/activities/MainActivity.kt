package com.drk.pmdmud10cp01.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.drk.pmdmud10cp01.R
import com.drk.pmdmud10cp01.adapter.PeliculaAdapter
import com.drk.pmdmud10cp01.databinding.ActivityMainBinding
import com.drk.pmdmud10cp01.model.Pelicula
import com.drk.pmdmud10cp01.viewmodel.PeliculaViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PeliculaAdapter
    private val viewModel: PeliculaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        title = "Películas"

        binding.rvPeliculas.layoutManager = LinearLayoutManager(this)
        adapter = PeliculaAdapter(mutableListOf()) { pelicula ->
            val intent = Intent(this, MapsActivity::class.java)
            intent.putExtra("id", pelicula.id)
            startActivity(intent)
        }
        binding.rvPeliculas.adapter = adapter
        registerForContextMenu(binding.rvPeliculas)

        observarViewModel()

        binding.srlDatos.setOnRefreshListener {
            binding.srlDatos.isRefreshing = false
        }
    }

    private fun observarViewModel() {
        viewModel.allPeliculas.observe(this) { lista ->
            if (lista.isEmpty()) {
                viewModel.cargarDatosIniciales()
            } else {
                adapter.setFilteredList(lista.toMutableList())
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val pelicula = adapter.getPelicula(adapter.posicionSeleccionada)

        return when (item.itemId) {
            R.id.menu_detalle -> {
                mostrarDetalle(pelicula)
                true
            }
            R.id.menu_editar -> {
                val intent = Intent(this, EditarActivity::class.java)
                intent.putExtra("id", pelicula.id)
                startActivity(intent)
                true
            }
            R.id.menu_eliminar -> {
                eliminarPelicula(pelicula)
                true
            }
            R.id.menu_hacer_foto -> {
                val intent = Intent(this, FotoActivity::class.java)
                intent.putExtra("id", pelicula.id)
                intent.putExtra("titulo", pelicula.titulo)
                startActivity(intent)
                true
            }
            R.id.menu_ver_foto -> {
                verFoto(pelicula)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun mostrarDetalle(pelicula: Pelicula) {
        AlertDialog.Builder(this)
            .setTitle(pelicula.titulo)
            .setMessage(
                "Director: ${pelicula.director}\n" +
                        "Año: ${pelicula.anio}\n" +
                        "Género: ${pelicula.genero}\n" +
                        "Sinopsis: ${pelicula.sinopsis}\n" +
                        "Latitud: ${pelicula.latitud}\n" +
                        "Longitud: ${pelicula.longitud}"
            )
            .setPositiveButton("Cerrar", null)
            .show()
    }

    private fun eliminarPelicula(pelicula: Pelicula) {
        AlertDialog.Builder(this)
            .setTitle("Eliminar")
            .setMessage("¿Eliminar ${pelicula.titulo}?")
            .setPositiveButton("Sí") { _, _ ->
                viewModel.delete(pelicula)
            }
            .setNegativeButton("No", null)
            .show()
    }

    private fun verFoto(pelicula: Pelicula) {
        if (pelicula.fotoPath.isEmpty()) {
            Toast.makeText(this, "No hay foto disponible", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse(pelicula.fotoPath), "image/*")
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.cerrar_sesion -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
