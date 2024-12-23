package com.example.ucp_2.repository.matakuliah


import com.example.ucp_2.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

interface RepositoryMataKuliah {
    suspend fun insertMataKuliah(matakuliah: MataKuliah)

    fun getAllMataKuliah(): Flow<List<MataKuliah>>

    suspend fun updateMataKuliah(matakuliah: MataKuliah)

    suspend fun deleteMataKuliah(matakuliah: MataKuliah)

    fun getDetailMataKuliah(kode: String): Flow<MataKuliah>
}