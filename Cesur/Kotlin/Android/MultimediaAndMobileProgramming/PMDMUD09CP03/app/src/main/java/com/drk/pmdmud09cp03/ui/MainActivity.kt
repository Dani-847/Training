package com.drk.pmdmud09cp03.ui

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.drk.pmdmud09cp03.adapter.PeliculaAdapter
import com.drk.pmdmud09cp03.dao.PeliculaDAO
import com.drk.pmdmud09cp03.model.Pelicula
import com.drk.pmdmud09cp03.R


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var peliculaAdapter: PeliculaAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var peliculaSeleccionada: Pelicula
    private lateinit var peliculaDAO: PeliculaDAO

    private val editActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                val nuevoTitulo = data?.getStringExtra("NUEVO_TITULO")
                if (nuevoTitulo != null) {
                    peliculaDAO.editarPelicula(peliculaSeleccionada, nuevoTitulo)
                    actualizarPeliculas()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.toolbar_peliculas)

        peliculaDAO = PeliculaDAO(this)
        peliculaDAO.insertarPeliculasIniciales()

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)

        val peliculasIniciales = peliculaDAO.getAllPeliculas()

        recyclerView = findViewById(R.id.rvPeliculas)
        recyclerView.layoutManager = LinearLayoutManager(this)

        peliculaAdapter = PeliculaAdapter(
            peliculasIniciales,
            { pelicula ->
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra("TITULO", pelicula.titulo)
                    putExtra("DESCRIPCION", pelicula.descripcion)
                    putExtra("IMAGEN", pelicula.img)
                    putExtra("ANHO", pelicula.anho)
                    putExtra("MINUTOS", pelicula.minutos)
                    putExtra("PAIS", pelicula.pais)
                }
                startActivity(intent)
            },
            { pelicula, view ->
                peliculaSeleccionada = pelicula
                view.showContextMenu()
                true
            }
        )

        recyclerView.adapter = peliculaAdapter

        swipeRefreshLayout.setOnRefreshListener {
            actualizarPeliculas()
            swipeRefreshLayout.isRefreshing = false
            Toast.makeText(this, "Lista recargada", Toast.LENGTH_SHORT).show()
        }

        registerForContextMenu(recyclerView)
    }

    private fun actualizarPeliculas() {
        val peliculas = peliculaDAO.getAllPeliculas()
        peliculaAdapter.actualizarPeliculas(peliculas)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as? SearchView

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false

            override fun onQueryTextChange(newText: String?): Boolean {
                val peliculasFiltradas = peliculaDAO.buscarPeliculas(newText.orEmpty())
                peliculaAdapter.actualizarPeliculas(peliculasFiltradas)
                return true
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_limpiar -> {
                peliculaDAO.limpiarPeliculas()
                actualizarPeliculas()
                Toast.makeText(this, "Lista limpiada", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
        menu?.setHeaderTitle(peliculaSeleccionada.titulo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_editar -> {
                val intent = Intent(this, EditActivity::class.java).apply {
                    putExtra("TITULO", peliculaSeleccionada.titulo)
                    putExtra("IMAGEN", peliculaSeleccionada.img)
                }
                editActivityResultLauncher.launch(intent)
                true
            }

            R.id.action_eliminar -> {
                AlertDialog.Builder(this)
                    .setTitle("¿Estas seguro de borrar ${peliculaSeleccionada.titulo}?")
                    .setPositiveButton("Si") { _, _ ->
                        peliculaDAO.eliminarPelicula(peliculaSeleccionada)
                        actualizarPeliculas()
                    }
                    .setNegativeButton("No", null)
                    .show()
                true
            }

            else -> super.onContextItemSelected(item)
        }
    }
}
