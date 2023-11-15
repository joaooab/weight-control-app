package com.br.weightcontrol.data.repository.impl

import com.br.weightcontrol.dao.TrackDao
import com.br.weightcontrol.data.mapper.toEntity
import com.br.weightcontrol.data.mapper.toModel
import com.br.weightcontrol.data.repository.TrackRepository
import com.br.weightcontrol.model.Track
import kotlinx.coroutines.flow.map

class TrackRepositoryImpl(private val dao: TrackDao) : TrackRepository {
    override fun getAllStream() = dao.getAllStream().map { it.toModel() }

    override fun getFirstStream() = dao.getFirstStream().map { it?.toModel() }

    override fun getLastStream() = dao.getLastStream().map { it?.toModel() }

    override fun getPreviewsStream() = dao.getPreviewsStream().map { it?.toModel() }

    override fun getHigherStream() = dao.getHigherStream().map { it?.toModel() }

    override fun getLowerStream() = dao.getLowerStream().map { it?.toModel() }

    override fun getByCreatedAt(createdAt: String) =
        dao.getByCreatedAt(createdAt).map { it?.toModel() }

    override suspend fun insert(track: Track) {
        dao.insert(track.toEntity())
    }

    override suspend fun update(track: Track) {
        dao.update(track.toEntity())
    }

    override suspend fun delete(track: Track) {
        dao.delete(track.toEntity())
    }
}
