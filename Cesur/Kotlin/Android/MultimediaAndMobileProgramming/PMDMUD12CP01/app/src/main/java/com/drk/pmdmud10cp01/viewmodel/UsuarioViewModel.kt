package com.drk.pmdmud10cp01.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.drk.pmdmud10cp01.database.AppDatabase
import com.drk.pmdmud10cp01.model.Usuario
import kotlinx.coroutines.launch

class UsuarioViewModel(application: Application) : AndroidViewModel(application) {

    private val usuarioDao = AppDatabase.getDatabase(application).usuarioDao()

    private val _loginExitoso = MutableLiveData<Boolean>()
    val loginExitoso: LiveData<Boolean> = _loginExitoso

    private val _registroExitoso = MutableLiveData<Boolean>()
    val registroExitoso: LiveData<Boolean> = _registroExitoso

    private val _mensaje = MutableLiveData<String>()
    val mensaje: LiveData<String> = _mensaje

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val usuario = usuarioDao.login(email, password)
            if (usuario != null) {
                _loginExitoso.value = true
            } else {
                _mensaje.value = "Credenciales incorrectas"
                _loginExitoso.value = false
            }
        }
    }

    fun registrar(nombre: String, email: String, password: String) {
        viewModelScope.launch {
            val existente = usuarioDao.obtenerPorEmail(email)
            if (existente != null) {
                _mensaje.value = "El email ya está registrado"
                _registroExitoso.value = false
                return@launch
            }
            usuarioDao.insertar(Usuario(nombre = nombre, email = email, password = password))
            _mensaje.value = "Usuario registrado correctamente"
            _registroExitoso.value = true
        }
    }
}
