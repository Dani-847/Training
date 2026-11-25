package com.drk.appproyectpm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.drk.appproyectpm.databinding.ActivityDetalleBinding

class DetalleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val fruta = intent.getSerializableExtra("fruta",Fruta::class.java)
        binding.tvDetalle.text = fruta!!.nombre
        binding.ivFrutaElegida.setImageResource(fruta!!.imagen)
        binding.tvColor.text="Color: ${fruta.color}"
        binding.tvOrigen.text="Origen: ${fruta.origen}"
        binding.tvTemporada.text="Temporada: ${fruta.temporada}"
        binding.tvPrecio.text="Precio: ${fruta.precioPorKilo}€/Kg"
        binding.tvVNutricional.text="Valor nutricional: ${fruta.valorNutricional}"
        binding.tvDescripcion.text = "Descripción: ${fruta.descripcion}"
        binding.btnVolver.setOnClickListener { finish() }
    }
}
