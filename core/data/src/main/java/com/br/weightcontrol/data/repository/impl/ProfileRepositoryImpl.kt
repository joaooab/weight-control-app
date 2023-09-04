package com.br.weightcontrol.data.repository.impl

import br.com.weightcontrol.datastore.datasource.ProfilePreferenceDataSource
import com.br.weightcontrol.data.repository.ProfileRepository
import com.br.weightcontrol.model.Profile

class ProfileRepositoryImpl(
    private val dataSource: ProfilePreferenceDataSource
) : ProfileRepository {

    override suspend fun stream() = dataSource.stream

    override suspend fun insert(profile: Profile) = dataSource.save(profile).isSuccess
}