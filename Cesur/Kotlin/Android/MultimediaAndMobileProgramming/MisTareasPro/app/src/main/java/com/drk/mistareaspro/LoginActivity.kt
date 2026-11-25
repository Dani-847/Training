package com.drk.mistareaspro

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.drk.mistareaspro.data.UserPreferences
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPassword: TextInputLayout
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var switchRecordar: SwitchMaterial
    private lateinit var btnLogin: MaterialButton
    private lateinit var btnRegistro: MaterialButton
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userPreferences = UserPreferences(this)
        initViews()
        loadSavedData()
        setupListeners()
        checkSession()
    }

    private fun initViews() {
        tilEmail = findViewById(R.id.tilEmail)
        tilPassword = findViewById(R.id.tilPassword)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        switchRecordar = findViewById(R.id.switchRecordar)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegistro = findViewById(R.id.btnRegistro)
    }

    private fun loadSavedData() {
        lifecycleScope.launch {
            val remember = userPreferences.remember.first()
            if (remember) {
                etEmail.setText(userPreferences.email.first())
                etPassword.setText(userPreferences.password.first())
                switchRecordar.isChecked = true
            }
        }
    }

    private fun checkSession() {
        lifecycleScope.launch {
            if (userPreferences.isLogged.first()) {
                navigateToMain()
            }
        }
    }

    private fun setupListeners() {
        btnLogin.setOnClickListener {
            if (validateFields()) {
                lifecycleScope.launch {
                    val registeredEmail = userPreferences.email.first()
                    val registeredPassword = userPreferences.password.first()
                    val isRegistered = userPreferences.userRegistered.first()

                    if (!isRegistered) {
                        Snackbar.make(it, "Usuario no registrado. Use 'Registro'", Snackbar.LENGTH_SHORT).show()
                        return@launch
                    }

                    if (etEmail.text.toString() == registeredEmail &&
                        etPassword.text.toString() == registeredPassword) {
                        if (switchRecordar.isChecked) {
                            userPreferences.saveCredentials(
                                etEmail.text.toString(),
                                etPassword.text.toString(),
                                true
                            )
                        }
                        userPreferences.setLogged(true)
                        navigateToMain()
                    } else {
                        Snackbar.make(it, "Credenciales incorrectas", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }

        btnRegistro.setOnClickListener {
            if (validateFields()) {
                lifecycleScope.launch {
                    userPreferences.saveCredentials(
                        etEmail.text.toString(),
                        etPassword.text.toString(),
                        switchRecordar.isChecked
                    )
                    userPreferences.setUserRegistered(true)
                    Snackbar.make(it, "Usuario registrado correctamente", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun validateFields(): Boolean {
        var isValid = true
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.error = "Email no válido"
            isValid = false
        } else {
            tilEmail.error = null
        }

        if (password.length < 8) {
            tilPassword.error = "Mínimo 8 caracteres"
            isValid = false
        } else {
            tilPassword.error = null
        }

        return isValid
    }

    private fun navigateToMain() {
        startActivity(Intent(this, TareasActivity::class.java))
        finish()
    }
}
