package br.com.weightcontrol.datastore.datasource

import androidx.datastore.core.DataStore
import br.com.weightcontrol.datastore.GenderPreference
import br.com.weightcontrol.datastore.ProfilePreference
import br.com.weightcontrol.datastore.copy
import com.br.weightcontrol.model.Gender
import com.br.weightcontrol.model.User
import com.br.weightcontrol.util.toLocalDate
import com.br.weightcontrol.util.toLong
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProfilePreferenceDataSource(private val preference: DataStore<ProfilePreference>) {

    val stream: Flow<User> = preference.data.map {
        User(
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

    suspend fun save(user: User) = runCatching {
        preference.updateData {
            it.copy {
                name = user.name
                height = user.height
                birthday = user.birthday.toLong()
                gender = when (user.gender) {
                    Gender.MALE -> GenderPreference.MALE
                    Gender.FEMALE -> GenderPreference.FEMALE
                    else -> GenderPreference.UNRECOGNIZED
                }
            }
        }
    }
}