package com.jlara.ejemplorv25v5_1.activities

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jlara.ejemplorv25v5_1.model.Fruta
import com.jlara.ejemplorv25v5_1.R
import com.jlara.ejemplorv25v5_1.adapter.FrutaAdapter
import com.jlara.ejemplorv25v5_1.dao.FrutaDAO
import com.jlara.ejemplorv25v5_1.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var listaFrutas:MutableList<Fruta>
    private lateinit var adapter: FrutaAdapter
    private lateinit var layoutManager: LayoutManager
    private lateinit var miDAO: FrutaDAO
    private var listaVacia=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        this.title = getString(R.string.frutas)
        miDAO = FrutaDAO()
        listaFrutas = miDAO.cargarLista(this)
        listaVacia=false
        layoutManager= LinearLayoutManager(this)
        binding.rvFrutas.layoutManager=layoutManager
        adapter=FrutaAdapter(listaFrutas){ fruta ->
            onItemSelected(fruta)
        }
        binding.rvFrutas.adapter=adapter
        binding.rvFrutas.setHasFixedSize(true)
        binding.rvFrutas.itemAnimator= DefaultItemAnimator()
        setupSwipeRefresh()
        this.onBackPressedDispatcher.addCallback(this , object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }

    private fun filterList(p0: String?) {
        if (p0 != null) {
            val query = p0.lowercase()
            val fullList = miDAO.cargarLista(this)

            val filteredList = if (query.isNotEmpty() && fullList.isNotEmpty()) {
                fullList.filter { it.nombre.lowercase().contains(query) }.toMutableList()
            } else {
                fullList.toMutableList()
            }

            if (filteredList.isEmpty() && query.isNotEmpty()) {
                Toast.makeText(this, "No existe esa fruta", Toast.LENGTH_SHORT).show()
            }

            adapter.setFilteredList(filteredList)
            listaFrutas = filteredList
            adapter.notifyDataSetChanged()
        }
    }

    private fun onItemSelected(fruta: Fruta) {
        Toast.makeText(this,"Has pulsado sobre ${fruta.nombre}", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        val buscar = menu?.findItem(R.id.buscador)
        val searchView = buscar?.actionView as SearchView
        searchView.setOnQueryTextListener(object : OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                filterList(p0)
                return true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.addFruta ->{
                addFruta()
                true
            }
            R.id.delete ->{
                limpiar()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun limpiar() {
        listaFrutas.clear()
        adapter.notifyDataSetChanged()
        listaVacia=true
    }

    private fun addFruta() {
        val frutaNueva = Fruta(
            listaFrutas[listaFrutas.size-1].id+1,"Nueva fruta ${System.currentTimeMillis()}"
            ,"Desconocida", R.drawable.plum_bg
        )
        listaFrutas.add(listaFrutas.size,frutaNueva)
        miDAO.insertarBBDD(this, frutaNueva)
        adapter.notifyItemInserted(listaFrutas.size)
        layoutManager.scrollToPosition(listaFrutas.size)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val frutaAfectada: Fruta = listaFrutas[item.groupId]

        when (item.itemId) {
            0 -> {
                val alert =
                    AlertDialog.Builder(this).setTitle("Eliminar ${frutaAfectada.nombre}")
                        .setMessage(
                            "¿Estas seguro de que quieres eliminar ${frutaAfectada.nombre}?"
                        )
                        .setNeutralButton("Cerrar", null).setPositiveButton(
                            "Aceptar"
                        ) { _, _ ->
                            display("Se ha eliminado ${frutaAfectada.nombre}")
                            listaFrutas.removeAt(item.groupId)
                            miDAO.eliminar(this,frutaAfectada)
                            adapter.notifyItemRemoved(item.groupId)
                            if (listaFrutas.isEmpty())
                                listaVacia=true
                        }.create()
                alert.show()
            }
            else -> return super.onContextItemSelected(item)
        }
        return true
    }

    private fun display(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun setupSwipeRefresh(){
        binding.srlDatos.setOnRefreshListener {
            val numItems = listaFrutas.size
            listaFrutas.clear()
            adapter.notifyItemRangeRemoved(0,numItems-1)
            listaFrutas = miDAO.cargarLista(this)
            adapter.setFilteredList(listaFrutas)
            adapter.notifyItemRangeInserted(0,listaFrutas.size-1)
            listaVacia=false
            binding.srlDatos.isRefreshing = false
        }
    }
}