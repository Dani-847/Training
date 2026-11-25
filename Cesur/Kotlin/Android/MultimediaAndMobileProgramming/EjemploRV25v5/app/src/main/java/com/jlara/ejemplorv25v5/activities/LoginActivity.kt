package com.jlara.ejemplorv25v5.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jlara.ejemplorv25v5.R
import com.jlara.ejemplorv25v5.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        this.title = getString(R.string.frutas)
        prefs = getSharedPreferences("app", MODE_PRIVATE)
        establecerValoresSiExisten()
        binding.btnLogin.setOnClickListener {
            val email=binding.etEmail.editText?.text.toString()
            val password=binding.etPassword.editText?.text.toString()
            if (login(email, password)) goToMain()
            guardarPreferencias(email,password)
        }
    }

    private fun eMailValido(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun passwordValida(password: String): Boolean {
        return password.length >= 8
    }

    private fun guardarPreferencias(email: String, password: String) {
        val editor = prefs.edit()
        if (binding.switch1.isChecked) {
            editor.putString("email", email)
            editor.putString("password", password)
            editor.putBoolean("recordar", true)
            editor.apply()
        } else {
            editor.clear()
            editor.putBoolean("recordar", false)
            editor.apply()
        }
    }

    private fun establecerValoresSiExisten() {
        val email = prefs.getString("email", "")
        val password = prefs.getString("password", "")
        val recordar = prefs.getBoolean("recordar", false)
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            binding.etEmail.editText!!.setText(email)
            binding.etPassword.editText!!.setText(password)
            binding.switch1.isChecked = recordar
        }
    }

    private fun login(email: String, password: String): Boolean {
        var valido = false
        if (!eMailValido(email)) {
            Toast.makeText(this,
                "e-mail no válido. Introduzca un e-mail correcto",
                Toast.LENGTH_SHORT).show()
        } else if (!passwordValida(password)) {
            Toast.makeText(this,
                "Password no válida. Debe tener al menos 8 caracteres",
                Toast.LENGTH_SHORT).show()
        } else {
            valido = true
        }
        return valido
    }

    private fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        //Evita que pasemos de nuevo a la activity login
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}
