package com.drk.pmdmud13cp01.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drk.pmdmud13cp01.data.repository.UsuarioRepository
import com.drk.pmdmud13cp01.model.Usuario
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: UsuarioRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<Result<Usuario>>()
    val loginResult: LiveData<Result<Usuario>> = _loginResult

    private val _registroResult = MutableLiveData<Result<Usuario>>()
    val registroResult: LiveData<Result<Usuario>> = _registroResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun login(email: String, password: String) {
        _isLoading.value = true
        viewModelScope.launch {
            val usuario = repository.login(email, password)
            if (usuario != null) {
                _loginResult.value = Result.success(usuario)
            } else {
                _loginResult.value = Result.failure(Exception("Credenciales incorrectas"))
            }
            _isLoading.value = false
        }
    }

    fun registrar(email: String, password: String) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = repository.registrar(email, password)
            _registroResult.value = result
            _isLoading.value = false
        }
    }

    fun validarEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validarPassword(password: String): Boolean {
        return password.length >= 4
    }
}
