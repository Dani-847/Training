package com.drk.ud09cp01

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val titulo = intent.getStringExtra("TITULO")
        val imagen = intent.getIntExtra("IMAGEN", 0)

        val toolbar: Toolbar = findViewById(R.id.toolbarEdit)
        setSupportActionBar(toolbar)
        supportActionBar?.title = titulo ?: ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val etTitulo = findViewById<EditText>(R.id.etTitulo)
        val ivPelicula = findViewById<ImageView>(R.id.ivPelicula)

        etTitulo.setText(titulo)
        ivPelicula.setImageResource(imagen)

        val btnCambiar = findViewById<Button>(R.id.btnCambiar)
        btnCambiar.setOnClickListener {
            val resultIntent = Intent().apply {
                putExtra("NUEVO_TITULO", etTitulo.text.toString())
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        val btnCancelar = findViewById<Button>(R.id.btnCancelar)
        btnCancelar.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
