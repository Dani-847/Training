package com.drk.examen21_11_2025.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.drk.examen21_11_2025.R
import com.drk.examen21_11_2025.adapter.ProductoAdapter
import com.drk.examen21_11_2025.dao.ProductoDAO
import com.drk.examen21_11_2025.databinding.ActivityMainBinding
import com.drk.examen21_11_2025.model.Producto
import com.google.android.material.snackbar.Snackbar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: ProductoAdapter
    private lateinit var listaProductos: MutableList<Producto>
    private lateinit var intentLaunch: ActivityResultLauncher<Intent>
    private lateinit var miDao: ProductoDAO
    private var listaVacia: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        iniciarLauncher()
        initComponents()
        setupSwipeRefresh()
    }

    private fun initComponents() {
        miDao = ProductoDAO()
        listaProductos = miDao.cargarListaOriginal(this).toMutableList()
        layoutManager = LinearLayoutManager(this)
        binding.rvProductos.layoutManager = layoutManager
        adapter = ProductoAdapter(listaProductos) { producto ->
            abrirDetalle(producto)
        }
        binding.rvProductos.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.limpiar -> {
                val n = listaProductos.size
                listaProductos.clear()
                miDao.borrarProductos(this)
                listaVacia = true
                adapter.notifyItemRangeRemoved(0, n)
                Snackbar.make(
                    binding.root,
                    "Se ha eliminado la lista completa",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
        return true
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val productoAfectado: Producto = listaProductos[item.groupId]

        when (item.itemId) {
            0 -> {
                AlertDialog.Builder(this)
                    .setTitle("Eliminar ${productoAfectado.nombre}")
                    .setMessage("¿Estas seguro de que quieres eliminar ${productoAfectado.nombre}?")
                    .setPositiveButton("Aceptar") { _, _ ->
                        val posicion = listaProductos.indexOf(productoAfectado)
                        listaProductos.removeAt(posicion)
                        miDao.eliminar(this, productoAfectado)
                        adapter.notifyItemRemoved(posicion)
                    }
                    .setNeutralButton("Cancelar", null)
                    .create()
                    .show()
            }

            1 -> {
                pantallaRecibirDatos(productoAfectado)
            }
        }
        return super.onContextItemSelected(item)
    }

    fun pantallaRecibirDatos(producto: Producto) {
        val intent = Intent(this, EditorActivity::class.java)
        intent.putExtra("producto", producto)
        intentLaunch.launch(intent)
    }

    private fun iniciarLauncher() {
        intentLaunch =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { respuesta ->
                if (respuesta.resultCode == RESULT_OK) {
                    val nuevoProducto: Producto? =
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            respuesta.data?.getSerializableExtra(
                                "productoEditado",
                                Producto::class.java
                            )
                        } else {
                            @Suppress("DEPRECATION")
                            respuesta.data?.getSerializableExtra("productoEditado") as? Producto
                        }

                    if (nuevoProducto != null) {
                        val posicion = listaProductos.indexOfFirst { productoViejo ->
                            productoViejo.id == nuevoProducto.id
                        }

                        if (posicion != -1) {
                            listaProductos[posicion] = nuevoProducto
                            miDao.actualizarBBDD(this, nuevoProducto)
                            adapter.notifyItemChanged(posicion)
                        }
                    }
                }
            }
    }

    private fun setupSwipeRefresh() {
        binding.main.setOnRefreshListener {
            listaProductos = miDao.cargarLista(this).toMutableList()
            adapter.setFilteredList(listaProductos)
            binding.main.isRefreshing = false
        }
    }

    private fun abrirDetalle(p: Producto) {
        val intent = Intent(this, DetalleActivity::class.java)
        intent.putExtra("producto", p)
        startActivity(intent)
    }

    private fun filterList(p0: String?) {
        if (p0 != null) {
            var filteredList = mutableListOf<Producto>()

            if (p0.isNotEmpty()) {
                val listaBase = miDao.cargarLista(this)
                for (i in listaBase) {
                    if (i.nombre.lowercase(Locale.ROOT)
                            .contains(p0.lowercase(Locale.ROOT))
                    ) {
                        filteredList.add(i)
                    }
                }
            } else if (listaProductos.isNotEmpty()) {
                filteredList = miDao.cargarLista(this).toMutableList()
            }

            if (filteredList.isEmpty()) {
                if (p0.isNotEmpty()) {
                    Toast.makeText(this, "No existe esa producto", Toast.LENGTH_SHORT).show()
                } else {
                    filteredList = mutableListOf()
                }
            }

            adapter.setFilteredList(filteredList)
            val numItems = listaProductos.size
            listaProductos.clear()
            adapter.notifyItemRangeRemoved(0, numItems)
            listaProductos = filteredList
            adapter.notifyItemRangeInserted(0, filteredList.size)
        }
    }
}
