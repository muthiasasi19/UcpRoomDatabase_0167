package com.example.ucp_2.repository.matakuliah

import com.example.ucp_2.data.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

interface RepositoryMK {

    //untuk mendapatkan semua data matakuliah dalam bentuk aliran data (flow)
    fun getAllMK (): Flow<List<Matakuliah>>

    // untuk mengambil data matakuliah berdasarkan Kode
    fun getMK(kode : String) : Flow<Matakuliah>

    // untuk menghapus data matakuliah
    suspend fun  deleteMK(matakuliah: Matakuliah)

    // untuk memperbarui data matakuliah didatabase
    suspend fun updateMK(matakuliah: Matakuliah)

    suspend fun insertMK(matakuliah: Matakuliah)

}