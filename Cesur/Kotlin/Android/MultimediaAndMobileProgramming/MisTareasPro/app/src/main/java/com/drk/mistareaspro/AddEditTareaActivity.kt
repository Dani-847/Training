package com.drk.mistareaspro

import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.drk.mistareaspro.data.Tarea
import com.drk.mistareaspro.data.TareasDatabaseHelper
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class AddEditTareaActivity : AppCompatActivity() {

    private lateinit var toolbar: MaterialToolbar
    private lateinit var etTitulo: TextInputEditText
    private lateinit var etDescripcion: TextInputEditText
    private lateinit var tvFecha: TextView
    private lateinit var etPrioridad: TextInputEditText
    private lateinit var cbCompletada: CheckBox
    private lateinit var btnGuardar: MaterialButton
    private lateinit var dbHelper: TareasDatabaseHelper

    private var tareaId: Long = -1
    private var selectedDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_tarea)

        dbHelper = TareasDatabaseHelper(this)
        tareaId = intent.getLongExtra("TAREA_ID", -1)

        initViews()
        setupListeners()

        if (tareaId != -1L) {
            loadTarea()
        }
    }

    private fun initViews() {
        toolbar = findViewById(R.id.toolbar)
        etTitulo = findViewById(R.id.etTitulo)
        etDescripcion = findViewById(R.id.etDescripcion)
        tvFecha = findViewById(R.id.tvFecha)
        etPrioridad = findViewById(R.id.etPrioridad)
        cbCompletada = findViewById(R.id.cbCompletada)
        btnGuardar = findViewById(R.id.btnGuardar)

        toolbar.setNavigationOnClickListener { finish() }
    }

    private fun setupListeners() {
        tvFecha.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Selected date")
                .build()

            datePicker.addOnPositiveButtonClickListener { selection ->
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                selectedDate = sdf.format(Date(selection))
                tvFecha.text = selectedDate
            }

            datePicker.show(supportFragmentManager, "DATE_PICKER")
        }

        btnGuardar.setOnClickListener {
            saveTarea()
        }
    }

    private fun loadTarea() {
        val tarea = dbHelper.getTareaById(tareaId)
        tarea?.let {
            etTitulo.setText(it.titulo)
            etDescripcion.setText(it.descripcion)
            tvFecha.text = it.fecha
            selectedDate = it.fecha
            etPrioridad.setText(it.prioridad.toString())
            cbCompletada.isChecked = it.completada
        }
    }

    private fun saveTarea() {
        val titulo = etTitulo.text.toString()
        val descripcion = etDescripcion.text.toString()
        val prioridad = etPrioridad.text.toString().toIntOrNull() ?: 1
        val completada = cbCompletada.isChecked

        if (titulo.isBlank()) {
            etTitulo.error = "Requerido"
            return
        }

        val tarea = Tarea(
            id = if (tareaId != -1L) tareaId else 0,
            titulo = titulo,
            descripcion = descripcion,
            fecha = selectedDate,
            prioridad = prioridad,
            completada = completada
        )

        if (tareaId != -1L) {
            dbHelper.updateTarea(tarea)
        } else {
            dbHelper.insertTarea(tarea)
        }

        finish()
    }
}
