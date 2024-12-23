package com.example.ucp_2.ui.viewmodel.matakuliah


import com.example.ucp_2.data.entity.Dosen
import com.example.ucp_2.data.entity.MataKuliah


    fun resetSnackBarMessage() {
        uiState = uiState.copy(snackBarMessage = null)
    }
}

data class MataKuliahEvent(
    val kode: String = "",
    val nama: String = "",
    val sks: String = "",
    val semester: String = "",
    val jenis: String = "",
    val dosenPengampu: String = ""
) {
    fun toMataKuliahEntity(): MataKuliah {
        return MataKuliah(
            kode = this.kode,
            nama = this.nama,
            sks = this.sks,
            semester = this.semester,
            jenis = this.jenis,
            dosenPengampu = this.dosenPengampu
        )
    }
}

data class MataKuliahUiState(
    val mataKuliahEvent: MataKuliahEvent = MataKuliahEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
    val dosentList: List<Dosen> = emptyList()
)

data class FormErrorState(
    val kode: String? = null,
    val nama: String? = null,
    val sks: String? = null,
    val semester: String? = null,
    val jenis: String? = null,
    val dosenPengampu: String? = null,
) {
    fun isValid(): Boolean {
        return kode == null && nama == null && sks == null &&
                semester == null && jenis == null && dosenPengampu == null
    }
}