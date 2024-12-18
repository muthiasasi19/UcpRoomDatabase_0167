package com.example.ucp_2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matakuliah")
data class Matakuliah(
    @PrimaryKey
    val kode: String,
    val namamk: String,
    val sks: String,
    val semester: String,
    val jenismk: String,
    val dosenpengampu: String
)