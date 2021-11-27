package app.cookery

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private const val PREFERENCES_FILE_NAME = "settings"

interface DataStore {

    fun isAppDataInitialized(): Flow<Boolean>

    suspend fun setAppInitializationState(isDataInitialized: Boolean)
}

@Singleton
class DataStoreManager @Inject constructor(
    private val context: Context
) : app.cookery.DataStore {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_FILE_NAME)
        private val IS_DATA_INITIALIZED = booleanPreferencesKey("IS_DATA_INITIALIZED")
    }

    override fun isAppDataInitialized(): Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_DATA_INITIALIZED] ?: false
        }

    override suspend fun setAppInitializationState(isDataInitialized: Boolean) {
        context.dataStore.edit {
            it[IS_DATA_INITIALIZED] = isDataInitialized
        }
    }
}
