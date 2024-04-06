package com.br.weightcontrol.data.repository.impl

import br.com.weightcontrol.datastore.datasource.ProfilePreferenceDataSource
import com.br.weightcontrol.data.repository.UserRepository
import com.br.weightcontrol.model.User
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val dataSource: ProfilePreferenceDataSource
) : UserRepository {

    override val stream = dataSource.stream.map { user ->
        if (user.name.isBlank()) null else user
    }

    override suspend fun insert(user: User) = dataSource.save(user).isSuccess
}