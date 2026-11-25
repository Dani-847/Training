package com.example.pmdmdam2u6cp0

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pmdmdam2u6cp0.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var listaPeliculas: MutableList<Pelicula> = mutableListOf()
    private lateinit var adapter: PeliculaAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

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
        listaPeliculas.addAll(PeliculaProvider.listaCarga)
        layoutManager = LinearLayoutManager(this)
        binding.rvPeliculas.layoutManager=layoutManager
        adapter = PeliculaAdapter(listaPeliculas)
        binding.rvPeliculas.adapter=adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.limpiar -> {
                var tamanho=listaPeliculas.size
                adapter.limpiar()
                adapter.notifyItemRangeRemoved(0,tamanho)
                true
            }
            R.id.recargar -> {
                listaPeliculas.addAll(adapter.recargar())
                var tamanho=listaPeliculas.size
                adapter.notifyItemRangeRemoved(0,tamanho)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}