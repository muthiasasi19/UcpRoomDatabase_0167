package com.example.ucp_2.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp_2.data.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

@Dao
interface MatakuliahDao {
    /* Tabel Mata Kuliah memiliki 5 operasi yaitu
    Create,
    Read,
    Update,
    Delete dan,
    Detail.
    */

    @Insert
    suspend fun  insertMatakuliah(matakuliah: Matakuliah)

    // Untuk mengambil semua data matakuliah yang tersimpan
    @Query("SELECT * FROM matakuliah ORDER BY namamk ASC")
    fun getAllMatakuliah() : Flow<List<Matakuliah>>

    // untuk mengambil data matakuliah bedasarkan kode
    @Query("SELECT * FROM matakuliah WHERE kode = :kode")
    fun getMatakuliah(kode : String) : Flow<Matakuliah>

    // Untuk menghapus data matakuliah tertentu dari database
    @Delete
    suspend fun deleteMatakuliah(matakuliah: Matakuliah)

    // untuk memperbarui informasi matakuliah yang sudah ada di database
    @Update
    suspend fun updateMatakuliah(matakuliah: Matakuliah)
}