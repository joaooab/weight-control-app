package br.com.weightcontrol.datastore.di

import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import br.com.weightcontrol.datastore.datasource.ProfilePreferenceDataSource
import br.com.weightcontrol.datastore.serializer.ProfilePreferencesSerializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    factory { ProfilePreferencesSerializer() }

    single {
        ProfilePreferenceDataSource(
            DataStoreFactory.create(
                serializer = get<ProfilePreferencesSerializer>(),
                scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
            ) {
                androidContext().dataStoreFile("profile_preference.pb")
            }
        )
    }
}