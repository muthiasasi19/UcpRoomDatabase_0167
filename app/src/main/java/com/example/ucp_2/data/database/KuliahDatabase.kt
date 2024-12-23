package com.example.ucp_2.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp_2.data.dao.DosenDao
import com.example.ucp_2.data.dao.MataKuliahDao
import com.example.ucp_2.data.entity.Dosen
import com.example.ucp_2.data.entity.MataKuliah

@Database(entities = [Dosen::class, MataKuliah::class], version = 1, exportSchema = false)
abstract class KuliahDatabase : RoomDatabase() {
    abstract fun dosenDao(): DosenDao
    abstract fun mataKuliahDao(): MataKuliahDao

    companion object {
        @Volatile
        private var Instance: KuliahDatabase? = null

        fun getDatabase(context: android.content.Context): KuliahDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    KuliahDatabase::class.java,
                    "KuliahDatabase"
                )
                    .build().also { Instance = it }
            }
        }
    }
}