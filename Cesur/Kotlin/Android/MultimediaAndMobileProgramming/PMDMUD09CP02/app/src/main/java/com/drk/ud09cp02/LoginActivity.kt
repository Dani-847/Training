package com.drk.ud09cp02

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.textfield.TextInputEditText
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

private val Context.dataStore by preferencesDataStore(name = "login_prefs")

class LoginActivity : AppCompatActivity() {

    private val EMAIL_KEY = stringPreferencesKey("EMAIL")
    private val PASSWORD_KEY = stringPreferencesKey("PASSWORD")
    private val REMEMBER_KEY = booleanPreferencesKey("REMEMBER")

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

        lifecycleScope.launch {
            val prefs = applicationContext.dataStore.data.first()
            val savedEmail: String = prefs[EMAIL_KEY] ?: ""
            val savedPassword: String = prefs[PASSWORD_KEY] ?: ""
            val isRemembered: Boolean = prefs[REMEMBER_KEY] ?: false

            if (isRemembered) {
                etEmail.setText(savedEmail)
                etPassword.setText(savedPassword)
                switchRecordar.isChecked = true
            }
        }

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            lifecycleScope.launch {
                applicationContext.dataStore.edit { prefs ->
                    if (switchRecordar.isChecked) {
                        prefs[EMAIL_KEY] = email
                        prefs[PASSWORD_KEY] = password
                        prefs[REMEMBER_KEY] = true
                    } else {
                        prefs.remove(EMAIL_KEY)
                        prefs.remove(PASSWORD_KEY)
                        prefs[REMEMBER_KEY] = false
                    }
                }

                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            }
        }
    }
}
