package com.drk.mistareaspro

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.drk.mistareaspro.adapter.TareasAdapter
import com.drk.mistareaspro.data.Tarea
import com.drk.mistareaspro.data.TareasDatabaseHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TareasActivity : AppCompatActivity() {

    private lateinit var rvTareas: RecyclerView
    private lateinit var tvEmpty: TextView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var adapter: TareasAdapter
    private lateinit var dbHelper: TareasDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tareas)

        dbHelper = TareasDatabaseHelper(this)
        initViews()
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        loadTareas()
    }

    private fun initViews() {
        rvTareas = findViewById(R.id.rvTareas)
        tvEmpty = findViewById(R.id.tvEmpty)
        fabAdd = findViewById(R.id.fabAdd)

        fabAdd.setOnClickListener {
            startActivity(Intent(this, AddEditTareaActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        adapter = TareasAdapter(
            mutableListOf(),
            onEdit = { tarea ->
                val intent = Intent(this, AddEditTareaActivity::class.java)
                intent.putExtra("TAREA_ID", tarea.id)
                startActivity(intent)
            },
            onDelete = { tarea ->
                dbHelper.deleteTarea(tarea.id)
                loadTareas()
            },
            onComplete = { tarea, isChecked ->
                dbHelper.updateTarea(tarea.copy(completada = isChecked))
                loadTareas()
            }
        )
        rvTareas.layoutManager = LinearLayoutManager(this)
        rvTareas.adapter = adapter
    }

    private fun loadTareas() {
        val tareas = dbHelper.getAllTareas()
        adapter.updateList(tareas)
        tvEmpty.visibility = if (tareas.isEmpty()) View.VISIBLE else View.GONE
        rvTareas.visibility = if (tareas.isEmpty()) View.GONE else View.VISIBLE
    }
}
