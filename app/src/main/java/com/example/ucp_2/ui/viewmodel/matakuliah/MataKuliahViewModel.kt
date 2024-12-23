package com.example.ucp_2.ui.viewmodel.matakuliah

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp_2.data.entity.Dosen
import com.example.ucp_2.data.entity.MataKuliah
import com.example.ucp_2.repository.dosen.RepositoryDosen
import com.example.ucp_2.repository.matakuliah.RepositoryMataKuliah
import kotlinx.coroutines.launch

class MataKuliahViewModel(
    private val repositoryMataKuliah: RepositoryMataKuliah, // Inisialisasi repository untuk berinteraksi dengan data MataKuliah
    private val repositoryDosen: RepositoryDosen // Inisialisasi repository untuk berinteraksi dengan data Dosen

) : ViewModel() {
    var uiState by mutableStateOf(MataKuliahUiState())
        private set

    var dosentList by mutableStateOf<List<Dosen>>(emptyList())
        private set
    init {
        viewModelScope.launch { // Melakukan operasi asynchronous menggunakan viewModelScope
            repositoryDosen.getAllDosen().collect {dosentList ->
                this@MataKuliahViewModel.dosentList = dosentList
                updateUiState()
            }
        }
    }
    private fun updateUiState(){
        uiState = uiState.copy(dosentList = dosentList)
    }

    fun updateState(mataKuliahEvent: MataKuliahEvent) { // Fungsi untuk memperbarui event MataKuliah

        uiState = uiState.copy(
            mataKuliahEvent = mataKuliahEvent
        )
    }

    private fun validateFields(): Boolean { // Fungsi untuk memvalidasi inputan form
        val event = uiState.mataKuliahEvent
        val errorState = FormErrorState(
            kode = if (event.kode.isNotEmpty()) null else "Kode tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            sks = if (event.sks.isNotEmpty()) null else "SKS tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "Semester tidak boleh kosong",
            jenis = if (event.jenis.isNotEmpty()) null else "Jenis tidak boleh kosong",
            dosenPengampu = if (event.dosenPengampu.isNotEmpty()) null else "Dosen Pengampu tidak boleh kosong"
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData() {
        val currentEvent = uiState.mataKuliahEvent
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryMataKuliah.insertMataKuliah(currentEvent.toMataKuliahEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        mataKuliahEvent = MataKuliahEvent(),
                        isEntryValid = FormErrorState()
                    )
                } catch (e: Exception) {
                    uiState = uiState.copy(
                        snackBarMessage = "Data gagal disimpan: ${e.message}"
                    )
                }
            }
        } else {
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data Anda"
            )
        }
    }

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