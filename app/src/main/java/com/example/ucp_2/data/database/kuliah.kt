package com.example.ucp_2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp_2.data.dao.MatakuliahDao
import com.example.ucp_2.data.entity.Matakuliah

//tabel matakuliah
@Database(entities = [Matakuliah::class], version = 1, exportSchema = false)
abstract class kuliah : RoomDatabase() {

    // Mendefinisikan fungsi untuk mengangkes data Matakuliah (Dao)
    abstract  fun matakuliahDao(): MatakuliahDao

    companion object {
        @Volatile // Memastikan bahwa nilai variable Instance selalu sama di semua tempat
        private  var Instance:kuliah? = null

        fun getDatabase(context: Context): kuliah {
            return (Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    kuliah::class.java, // Class Database
                    "KrsDatabase" // Nama Database
                )
                    .build().also { Instance = it }
            })
        }
    }
}