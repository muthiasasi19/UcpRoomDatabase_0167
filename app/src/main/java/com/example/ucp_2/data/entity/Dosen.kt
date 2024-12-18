package com.example.ucp_2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dosen")
data class Dosen(
    @PrimaryKey
    val nidn: String, // yg Primary key hanya satu baris dibawah @PrimaryKey saja // knp nim tidak int? karena datanya tidak dikomputasi nantinya
    val nama: String,
    val jenisKelamin: String
)