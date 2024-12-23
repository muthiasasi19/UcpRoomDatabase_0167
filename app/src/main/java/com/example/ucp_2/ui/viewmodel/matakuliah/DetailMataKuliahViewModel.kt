package com.example.ucp_2.ui.viewmodel.matakuliah


import com.example.ucp_2.data.entity.MataKuliah


data class DetailUiState(
    val detailUiEvent :MataKuliahEvent = MataKuliahEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val erorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == MataKuliahEvent()

    val isEventNotEmpty: Boolean
        get() = detailUiEvent != MataKuliahEvent()

}

fun MataKuliah.toDetailUiEvent(): MataKuliahEvent{
    return MataKuliahEvent(
        kode = kode,
        nama = nama,
        sks = sks,
        semester = semester,
        jenis = jenis,
        dosenPengampu = dosenPengampu
    )
}