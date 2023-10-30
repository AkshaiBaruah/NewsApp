package com.loc.newsapp.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.loc.newsapp.domain.manager.LocalUserManager
import com.loc.newsapp.util.constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(val context : Context) : LocalUserManager {
    override suspend fun saveAppEntry() {
        context.datastore.edit {
            settings -> settings[PreferencesKeys.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.datastore.data.map {preferences ->
            preferences[PreferencesKeys.APP_ENTRY] ?: false
        }
    }
}

//make the datastore object as an extension of context, so we can access it through the context
private val Context.datastore : DataStore<Preferences> by preferencesDataStore(constants.USER_SETTINGS)

//all the keys
private object PreferencesKeys{
    //this is the appentry  key
    val APP_ENTRY = booleanPreferencesKey(constants.APP_ENTRY)
}

