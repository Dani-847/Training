package com.drk.mistareaspro.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs1")

class UserPreferences(private val context: Context) {

    companion object {
        val EMAIL_KEY = stringPreferencesKey("email")
        val PASSWORD_KEY = stringPreferencesKey("password")
        val REMEMBER_KEY = booleanPreferencesKey("remember")
        val IS_LOGGED_KEY = booleanPreferencesKey("is_logged")
        val USER_REGISTERED_KEY = booleanPreferencesKey("user_registered")
    }

    val email: Flow<String> = context.dataStore.data.map { it[EMAIL_KEY] ?: "" }
    val password: Flow<String> = context.dataStore.data.map { it[PASSWORD_KEY] ?: "" }
    val remember: Flow<Boolean> = context.dataStore.data.map { it[REMEMBER_KEY] ?: false }
    val isLogged: Flow<Boolean> = context.dataStore.data.map { it[IS_LOGGED_KEY] ?: false }
    val userRegistered: Flow<Boolean> = context.dataStore.data.map { it[USER_REGISTERED_KEY] ?: false }

    suspend fun saveCredentials(email: String, password: String, remember: Boolean) {
        context.dataStore.edit {
            it[EMAIL_KEY] = email
            it[PASSWORD_KEY] = password
            it[REMEMBER_KEY] = remember
        }
    }

    suspend fun setLogged(logged: Boolean) {
        context.dataStore.edit { it[IS_LOGGED_KEY] = logged }
    }

    suspend fun setUserRegistered(registered: Boolean) {
        context.dataStore.edit { it[USER_REGISTERED_KEY] = registered }
    }

    suspend fun clearSession() {
        context.dataStore.edit {
            it[IS_LOGGED_KEY] = false
        }
    }
}
