package app.cookery

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Singleton

private const val PREFERENCES_FILE_NAME = "settings"

@Singleton
class DataStoreManager(val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_FILE_NAME)
        val IS_DATA_INITIALIZED = booleanPreferencesKey("IS_DATA_INITIALIZED")
    }

    suspend fun saveAppInitializationState(isDataInitialized: Boolean) {
        context.dataStore.edit {
            it[IS_DATA_INITIALIZED] = isDataInitialized
        }
    }

    fun isAppDataInitialized(): Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_DATA_INITIALIZED] ?: false
        }
}
