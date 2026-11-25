package com.drk.pmdmud13cp01.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.drk.pmdmud13cp01.R
import com.drk.pmdmud13cp01.data.local.PeliculaDatabase
import com.drk.pmdmud13cp01.data.repository.UsuarioRepository
import com.drk.pmdmud13cp01.databinding.ActivityRegistroBinding
import com.drk.pmdmud13cp01.viewmodel.AuthViewModel
import com.google.android.material.snackbar.Snackbar

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding

    private val viewModel: AuthViewModel by viewModels {
        val database = PeliculaDatabase.getDatabase(applicationContext)
        AuthViewModelFactory(UsuarioRepository(database.usuarioDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupListeners()
        observeViewModel()
    }

    private fun setupListeners() {
        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            if (validateInputs(email, password, confirmPassword)) {
                viewModel.registrar(email, password)
            }
        }

        binding.fabBack.setOnClickListener {
            finish()
        }
    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.btnRegister.isEnabled = !isLoading
        }

        viewModel.registroResult.observe(this) { result ->
            result.onSuccess {
                // Mostrar snackbar de éxito con icono de Oscar
                Snackbar.make(
                    binding.main,
                    getString(R.string.exito_registro),
                    Snackbar.LENGTH_LONG
                ).show()

                // Volver al login después de un breve delay
                binding.main.postDelayed({
                    finish()
                }, 1500)
            }.onFailure { exception ->
                val message = when {
                    exception.message?.contains("ya está registrado") == true ->
                        getString(R.string.error_email_registrado)
                    else -> exception.message ?: "Error desconocido"
                }
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateInputs(email: String, password: String, confirmPassword: String): Boolean {
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, getString(R.string.error_campos_vacios), Toast.LENGTH_SHORT).show()
            return false
        }

        if (!viewModel.validarEmail(email)) {
            Toast.makeText(this, getString(R.string.error_email_invalido), Toast.LENGTH_SHORT).show()
            return false
        }

        if (!viewModel.validarPassword(password)) {
            Toast.makeText(this, getString(R.string.error_password_corta), Toast.LENGTH_SHORT).show()
            return false
        }

        if (password != confirmPassword) {
            Toast.makeText(this, getString(R.string.error_passwords_no_coinciden), Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}
