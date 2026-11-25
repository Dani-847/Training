package com.drk.pmdmud10cp01.activities

import android.os.Bundle
import android.widget.Toast
import com.drk.pmdmud10cp01.R
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import coil.load
import com.drk.pmdmud10cp01.database.AppDatabase
import com.drk.pmdmud10cp01.databinding.ActivityEditarBinding
import com.drk.pmdmud10cp01.model.Pelicula
import com.drk.pmdmud10cp01.viewmodel.PeliculaViewModel
import kotlinx.coroutines.launch


class EditarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditarBinding
    private val viewModel: PeliculaViewModel by viewModels()
    private var peliculaId: Int = 0
    private var peliculaActual: Pelicula? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEditarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        peliculaId = intent.getIntExtra("id", 0)

        cargarPelicula()

        binding.btnGuardar.setOnClickListener { guardar() }
    }

    private fun cargarPelicula() {
        lifecycleScope.launch {
            val dao = AppDatabase.getDatabase(this@EditarActivity).peliculaDao()
            peliculaActual = dao.getPeliculaById(peliculaId)
            peliculaActual?.let { p ->
                title = "Editar ${p.titulo}"
                binding.etTitulo.editText?.setText(p.titulo)
                binding.etDirector.editText?.setText(p.director)
                binding.etAnio.editText?.setText(p.anio.toString())
                binding.etGenero.editText?.setText(p.genero)
                binding.etSinopsis.editText?.setText(p.sinopsis)

                // Cargar imagen con Coil
                if (p.imagenUrl.isNotEmpty()) {
                    binding.ivPoster.load(p.imagenUrl) {
                        placeholder(R.drawable.no_imagen)
                        error(R.drawable.no_imagen)
                    }
                }
            }
        }
    }


    private fun guardar() {
        val titulo = binding.etTitulo.editText?.text.toString().trim()
        val director = binding.etDirector.editText?.text.toString().trim()
        val anio = binding.etAnio.editText?.text.toString().toIntOrNull() ?: 0
        val genero = binding.etGenero.editText?.text.toString().trim()
        val sinopsis = binding.etSinopsis.editText?.text.toString().trim()

        if (titulo.isEmpty() || director.isEmpty() || genero.isEmpty()) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        peliculaActual?.let { p ->
            val actualizado = p.copy(
                titulo = titulo,
                director = director,
                anio = anio,
                genero = genero,
                sinopsis = sinopsis
            )
            viewModel.update(actualizado)
            Toast.makeText(this, "Película actualizada", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
