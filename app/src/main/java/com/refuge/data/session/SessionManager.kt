package com.refuge.data.session

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("session")

class SessionManager(private val context: Context) {

    companion object {
        val USER_ID = intPreferencesKey("user_id")
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_EMAIL = stringPreferencesKey("user_email")
        val IS_LOGGED = booleanPreferencesKey("is_logged")
    }

    suspend fun saveSession(
        userId: Int,
        userName: String,
        userEmail: String
    ) {
        context.dataStore.edit { prefs ->
            prefs[USER_ID] = userId
            prefs[USER_NAME] = userName
            prefs[USER_EMAIL] = userEmail
            prefs[IS_LOGGED] = true
        }
    }

    fun getUserId(): Flow<Int?> =
        context.dataStore.data.map { it[USER_ID] }

    fun getUserName(): Flow<String?> =
        context.dataStore.data.map { it[USER_NAME] }

    fun getUserEmail(): Flow<String?> =
        context.dataStore.data.map { it[USER_EMAIL] }

    fun isLogged(): Flow<Boolean> =
        context.dataStore.data.map { it[IS_LOGGED] ?: false }

    suspend fun logout() {
        context.dataStore.edit { it.clear() }
    }
}