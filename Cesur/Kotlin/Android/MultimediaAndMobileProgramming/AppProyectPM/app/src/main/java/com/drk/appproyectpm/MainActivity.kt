package com.drk.appproyectpm

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.android.material.snackbar.Snackbar
import com.drk.appproyectpm.adapter.FrutaAdapter
import com.drk.appproyectpm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var listaFrutas:MutableList<Fruta>
    private lateinit var adapter: FrutaAdapter
    private lateinit var layoutManager: LayoutManager
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
        listaFrutas = FrutaProvider.cargarLista()
        layoutManager= LinearLayoutManager(this)
        binding.rvFrutas.layoutManager=layoutManager
        adapter=FrutaAdapter(listaFrutas){ fruta ->
            onItemSelected(fruta)
        }
        binding.rvFrutas.adapter=adapter
        binding.rvFrutas.setHasFixedSize(true)
        binding.rvFrutas.itemAnimator= DefaultItemAnimator()
        setupSwipeRefresh()
    }

    private fun onItemSelected(fruta: Fruta) {
        var intent = Intent(this, DetalleActivity::class.java)
        intent.putExtra("fruta",fruta)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.addFruta ->{
                addFruta()
                true
            }
            R.id.delete ->{
                val alert =
                AlertDialog.Builder(this).setTitle("Eliminar todas las frutas")
                    .setMessage(
                        "¿Estas seguro de que quieres eliminar todas las frutas?"
                    )
                    .setNeutralButton("Cerrar", null).setPositiveButton(
                        "Aceptar"
                    ) { _, _ ->
                        limpiar()
                    }.create()
                alert.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun limpiar() {
        val tamanho = listaFrutas.size
        listaFrutas.clear()
        adapter.notifyItemRangeRemoved(0,tamanho)
        display("Se han eliminado todas las frutas")
    }

    private fun addFruta() {
        listaFrutas.add(listaFrutas.size,Fruta("Nueva fruta ${listaFrutas.size}"
            ,"Desconocida",R.drawable.plum_bg, 9.99, "España",
            "Todo el año", "Morado", "Muchas vitaminas"))
        adapter.notifyItemInserted(listaFrutas.size)
        layoutManager.scrollToPosition(listaFrutas.size)
    }

    private fun display(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
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
                            adapter.notifyItemRemoved(item.groupId)
                        }.create()
                alert.show()
            }
            else -> return super.onContextItemSelected(item)
        }
        return true
    }

    private fun setupSwipeRefresh(){
        binding.main.setOnRefreshListener {
            val numItems=listaFrutas.size
            listaFrutas.clear()
            adapter.notifyItemRangeRemoved(0,numItems-1)
            listaFrutas=FrutaProvider.cargarLista()
            adapter.notifyItemRangeInserted(0,listaFrutas.size-1)
            adapter.setFilteredList(listaFrutas)
            binding.main.isRefreshing = false
        }
    }
}

