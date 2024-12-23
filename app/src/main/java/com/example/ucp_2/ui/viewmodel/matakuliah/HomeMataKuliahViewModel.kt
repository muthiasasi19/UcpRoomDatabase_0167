package com.example.ucp_2.ui.viewmodel.matakuliah


import com.example.ucp_2.data.entity.MataKuliah

data class HomeUiState(
    val listMataKuliah: List<MataKuliah> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)