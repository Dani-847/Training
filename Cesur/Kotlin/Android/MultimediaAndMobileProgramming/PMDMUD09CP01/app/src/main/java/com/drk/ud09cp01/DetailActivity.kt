package com.drk.ud09cp01

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val titulo = intent.getStringExtra("TITULO")
        val descripcion = intent.getStringExtra("DESCRIPCION")
        val imagen = intent.getIntExtra("IMAGEN", 0)
        val anho = intent.getIntExtra("ANHO", 0)
        val minutos = intent.getIntExtra("MINUTOS", 0)
        val pais = intent.getStringExtra("PAIS") ?: ""

        val toolbar: Toolbar = findViewById(R.id.toolbarDetail)
        setSupportActionBar(toolbar)
        supportActionBar?.title = titulo ?: ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val ivPeliculaDetalle = findViewById<ImageView>(R.id.ivPeliculaDetalle)
        val tvTituloDetalle = findViewById<TextView>(R.id.tvTituloDetalle)
        val tvDescripcionDetalle = findViewById<TextView>(R.id.tvDescripcionDetalle)
        val tvAnho = findViewById<TextView>(R.id.tvAnho)
        val tvMinutos = findViewById<TextView>(R.id.tvMinutos)
        val tvPais = findViewById<TextView>(R.id.tvPais)

        ivPeliculaDetalle.setImageResource(imagen)
        tvTituloDetalle.text = titulo
        tvDescripcionDetalle.text = descripcion
        tvAnho.text = getString(R.string.detail_year_prefix, anho)
        tvMinutos.text = getString(R.string.detail_minutes_prefix, minutos)
        tvPais.text = getString(R.string.detail_country_prefix, pais)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
