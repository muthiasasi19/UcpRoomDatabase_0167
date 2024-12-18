package com.example.ucp_2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucp_2.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

@Dao
interface DosenDao {
    //insert
    @Insert
    suspend fun  insertDosen(dosen: Dosen)

    //read nya belooommmm-------------------------------

    // Untuk mengambil semua data dosen yang tersimpan
    @Query("SELECT * FROM dosen ORDER BY nama ASC")
    fun getAllDosen() : Flow<List<Dosen>>

    // untuk mengambil data dosen bedasarkan nidn
    @Query("SELECT * FROM dosen WHERE nidn = :nidn")
    fun getDosen(nidn : String) : Flow<Dosen>

}