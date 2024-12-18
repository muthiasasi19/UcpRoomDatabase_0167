package com.example.ucp_2.repository.dosen

import com.example.ucp_2.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

interface RepositoryDsn {

    /*
     Tabel Dosen memiliki 2 operasi yaitu create dan read saja.
    */

    //untuk mendapatkan semua data dosen dalam bentuk aliran data (flow)
    fun getAllDsn (): Flow<List<Dosen>>

    // untuk mengambil data dosen berdasarkan Kode
    fun getDsn(nidn : String) : Flow<Dosen>


    suspend fun insertDsn(dosen: Dosen)

}