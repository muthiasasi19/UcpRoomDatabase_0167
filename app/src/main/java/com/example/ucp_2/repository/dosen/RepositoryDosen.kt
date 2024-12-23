package com.example.ucp_2.repository.dosen

import com.example.ucp_2.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

interface RepositoryDosen {
    suspend fun insertDosen(dosen: Dosen)

    fun getAllDosen(): Flow<List<Dosen>>

    fun getDosen(nidn: String) : Flow<Dosen>
}