package com.drk.ud09cp01

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val toolbar: Toolbar = findViewById(R.id.toolbarLogin)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.app_name)

        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val switchRecordar = findViewById<MaterialSwitch>(R.id.switchRecordar)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        val sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)

        val savedEmail = sharedPreferences.getString("EMAIL", "")
        val savedPassword = sharedPreferences.getString("PASSWORD", "")
        val isRemembered = sharedPreferences.getBoolean("REMEMBER", false)

        if (isRemembered) {
            etEmail.setText(savedEmail)
            etPassword.setText(savedPassword)
            switchRecordar.isChecked = true
        }

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (switchRecordar.isChecked) {
                sharedPreferences.edit().apply {
                    putString("EMAIL", email)
                    putString("PASSWORD", password)
                    putBoolean("REMEMBER", true)
                    apply()
                }
            } else {
                sharedPreferences.edit().clear().apply()
            }

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
