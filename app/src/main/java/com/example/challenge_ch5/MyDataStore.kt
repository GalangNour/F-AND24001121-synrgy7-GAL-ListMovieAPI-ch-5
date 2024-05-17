package com.example.challenge_ch5

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class MyDataStore(private val context: Context) {

    companion object {
        val USERNAME = stringPreferencesKey("username")
        val PASSWORD = stringPreferencesKey("password")
        val EMAIL = stringPreferencesKey("email")
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    }


    fun isUserLoggedIn(): Flow<Boolean> {
        return context.dataStore.data.catch {
            emit(emptyPreferences())
        }.map { preference ->
            preference[IS_LOGGED_IN] ?: false
        }
    }

    suspend fun setUserLoggedIn(isUserLoggedIn: Boolean) {
        context.dataStore.edit { preference ->
            preference[IS_LOGGED_IN] = isUserLoggedIn
        }
    }


    suspend fun saveUser(username: String, password: String, email: String) {
        context.dataStore.edit { preferences ->
            preferences[USERNAME] = username
            preferences[PASSWORD] = password
            preferences[EMAIL] = email
        }
    }

    fun getUsername() : Flow<String>{
        return context.dataStore.data.map { preferences ->
            preferences[USERNAME] ?: ""
        }
    }

    fun getEmail() : Flow<String>{
        return context.dataStore.data.map { preferences ->
            preferences[EMAIL] ?: ""
        }
    }

    fun getPassword() : Flow<String>{
        return context.dataStore.data.map { preferences ->
            preferences[PASSWORD] ?: ""
        }
    }



}
