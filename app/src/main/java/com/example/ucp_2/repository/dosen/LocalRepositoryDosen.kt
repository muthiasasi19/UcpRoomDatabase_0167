package com.example.ucp_2.repository.dosen

import com.example.ucp_2.data.dao.DosenDao
import com.example.ucp_2.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

class LocalRepositoryDsn(
    private val dosenDao: DosenDao
) : RepositoryDosen {

    override suspend fun insertDosen(dosen: Dosen) {
        dosenDao.insertDosen(dosen)
    }

    override fun getAllDosen(): Flow<List<Dosen>> {
        return dosenDao.getAllDosen()
    }

    override fun getDosen(nidn: String) : Flow<Dosen>{
        return dosenDao.getDosen(nidn)
    }
}