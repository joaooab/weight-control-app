package com.br.weightcontrol.data.repository.impl

import br.com.weightcontrol.datastore.datasource.ProfilePreferenceDataSource
import com.br.weightcontrol.data.repository.UserRepository
import com.br.weightcontrol.model.User

class UserRepositoryImpl(
    private val dataSource: ProfilePreferenceDataSource
) : UserRepository {

    override val userData = dataSource.stream

    override suspend fun insert(user: User) = dataSource.save(user).isSuccess
}