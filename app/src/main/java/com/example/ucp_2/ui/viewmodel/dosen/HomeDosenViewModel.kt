package com.example.ucp_2.ui.viewmodel.dosen

import com.example.ucp_2.data.entity.Dosen




data class HomeUiState(
    val listDosen: List<Dosen> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)