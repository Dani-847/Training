package com.drk.examen21_11_2025.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.drk.examen21_11_2025.R
import com.drk.examen21_11_2025.databinding.ActivityEditorBinding
import com.drk.examen21_11_2025.model.Producto

class EditorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditorBinding
    private lateinit var productoOriginal: Producto

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
        binding = ActivityEditorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        productoOriginal = getProductoExtra() ?: run {
            finish()
            return
        }

        binding.imagen.setImageResource(productoOriginal.imagen)
        binding.editNombre.setText(productoOriginal.nombre)
        binding.editCategoria.setText(productoOriginal.categoria)
        binding.editDescripcion.setText(productoOriginal.descripcion)
        binding.editPrecio.setText(productoOriginal.precio.toString())

        binding.btnCancelar.setOnClickListener {
            finish()
        }

        binding.btnCambiar.setOnClickListener {
            val nombreEditado = binding.editNombre.text.toString().trim()
            val categoriaEditada = binding.editCategoria.text.toString().trim()
            val descripcionEditada = binding.editDescripcion.text.toString().trim()
            val precioTexto = binding.editPrecio.text.toString().trim()

            if (nombreEditado.isEmpty() ||
                categoriaEditada.isEmpty() ||
                descripcionEditada.isEmpty() ||
                precioTexto.isEmpty()
            ) {
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val precioEditado = precioTexto.toDoubleOrNull()
            if (precioEditado == null) {
                Toast.makeText(this, "Precio no válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Actualizar el objeto original
            productoOriginal.nombre = nombreEditado
            productoOriginal.categoria = categoriaEditada
            productoOriginal.descripcion = descripcionEditada
            productoOriginal.precio = precioEditado

            val intent = Intent()
            intent.putExtra("productoEditado", productoOriginal)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}
