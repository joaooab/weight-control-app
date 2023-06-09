package br.com.weightcontrol.datastore.datasource

import android.util.Log
import androidx.datastore.core.DataStore
import br.com.weightcontrol.datastore.GenderPreference
import br.com.weightcontrol.datastore.ProfilePreference
import br.com.weightcontrol.datastore.copy
import com.br.weightcontrol.model.Gender
import com.br.weightcontrol.model.Profile
import com.br.weightcontrol.util.toLocalDate
import com.br.weightcontrol.util.toLong
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.IOException

class ProfilePreferenceDataSource(private val preference: DataStore<ProfilePreference>) {

    val stream: Flow<Profile> = preference.data.map {
        Profile(
            name = it.name,
            height = it.height,
            birthday = it.birthday.toLocalDate(),
            gender = when (it.gender) {
                GenderPreference.MALE -> Gender.MALE
                GenderPreference.FEMALE -> Gender.FEMALE
                else -> Gender.UNKNOWN
            }
        )
    }

    suspend fun save(profile: Profile) = try {
        preference.updateData {
            it.copy {
                name = profile.name
                height = profile.height
                birthday = profile.birthday.toLong()
                gender = when (profile.gender) {
                    Gender.MALE -> GenderPreference.MALE
                    Gender.FEMALE -> GenderPreference.FEMALE
                    else -> GenderPreference.UNRECOGNIZED
                }
            }
        }
    } catch (ioException: IOException) {
        Log.e("ProfilePreferences", "Failed to update preferences", ioException)
    }
}