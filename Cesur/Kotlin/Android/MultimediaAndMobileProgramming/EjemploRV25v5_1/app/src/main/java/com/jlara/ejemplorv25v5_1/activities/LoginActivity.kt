package com.jlara.ejemplorv25v5_1.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.jlara.ejemplorv25v5_1.R
import com.jlara.ejemplorv25v5_1.databinding.ActivityLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "MI_DATA_STORE")

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        this.title = getString(R.string.frutas)
        // Leer datos guardados si existen
        lifecycleScope.launch(Dispatchers.IO) {
            leerDataStore().collect { datos ->
                withContext(Dispatchers.Main) {
                    val partes = datos.split("-")
                    if (partes.size >= 2 && partes[0].isNotEmpty() && partes[1].isNotEmpty()) {
                        binding.etEmail.editText?.setText(partes[0])
                        binding.etPassword.editText?.setText(partes[1])
                        binding.recordar.isChecked = true
                    }
                }
            }
        }

        // Evento click del botón login
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.editText?.text.toString()
            val password = binding.etPassword.editText?.text.toString()

            lifecycleScope.launch(Dispatchers.IO) {
                if (login(email, password)) {
                    if (binding.recordar.isChecked) {
                        guardarDataStore(email, password)
                    } else {
                        borrarDataStore()
                    }
                    withContext(Dispatchers.Main) {
                        goToMain()
                    }
                }
            }
        }
    }

    private suspend fun guardarDataStore(email: String, password: String) {
        dataStore.edit { editor ->
            editor[stringPreferencesKey("email")] = email
            editor[stringPreferencesKey("password")] = password
        }
    }

    private fun leerDataStore(): Flow<String> {
        return dataStore.data.map { prefs ->
            val email = prefs[stringPreferencesKey("email")] ?: ""
            val password = prefs[stringPreferencesKey("password")] ?: ""
            "$email-$password"
        }
    }

    private suspend fun borrarDataStore() {
        dataStore.edit { editor ->
            editor.remove(stringPreferencesKey("email"))
            editor.remove(stringPreferencesKey("password"))
        }
    }

    private fun eMailValido(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun passwordValida(password: String): Boolean {
        return password.length >= 8
    }

    private fun login(email: String, password: String): Boolean {
        return if (!eMailValido(email)) {
            runOnUiThread {
                Toast.makeText(
                    this,
                    "e-mail no válido. Introduzca un e-mail correcto",
                    Toast.LENGTH_SHORT
                ).show()
            }
            false
        } else if (!passwordValida(password)) {
            runOnUiThread {
                Toast.makeText(
                    this,
                    "Password no válida. Debe tener al menos 8 caracteres",
                    Toast.LENGTH_SHORT
                ).show()
            }
            false
        } else {
            true
        }
    }

    private fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}
