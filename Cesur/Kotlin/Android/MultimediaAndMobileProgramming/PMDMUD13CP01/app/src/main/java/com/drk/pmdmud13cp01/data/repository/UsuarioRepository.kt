package com.drk.pmdmud13cp01.data.repository

import com.drk.pmdmud13cp01.data.local.UsuarioDao
import com.drk.pmdmud13cp01.model.Usuario

class UsuarioRepository(private val usuarioDao: UsuarioDao) {

    suspend fun login(email: String, password: String): Usuario? {
        return usuarioDao.login(email, password)
    }

    suspend fun registrar(email: String, password: String): Result<Usuario> {
        return try {
            if (usuarioDao.existeEmail(email)) {
                Result.failure(Exception("El email ya está registrado"))
            } else {
                val usuario = Usuario(email = email, password = password)
                val id = usuarioDao.insertUsuario(usuario)
                Result.success(usuario.copy(id = id.toInt()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun existeEmail(email: String): Boolean {
        return usuarioDao.existeEmail(email)
    }
}
