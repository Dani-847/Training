package com.drk.examen21_11_2025.activities

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.drk.examen21_11_2025.R
import com.drk.examen21_11_2025.databinding.ActivityDetalleBinding
import com.drk.examen21_11_2025.model.Producto

class DetalleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalleBinding

    @Suppress("DEPRECATION")
    private fun getProductoExtra(): Producto? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("producto", Producto::class.java)
        } else {
            intent.getSerializableExtra("producto") as? Producto
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val p = getProductoExtra() ?: return

        binding.imagenDetalle.setImageResource(p.imagen)
        binding.txtNombre.text = p.nombre
        binding.txtCategoria.text = "Categoría: ${p.categoria}"
        binding.txtDescripcion.text = p.descripcion
        binding.txtPrecio.text = "Precio: ${p.precio} €"

        binding.btnVolver.setOnClickListener {
            finish()
        }
    }
}
