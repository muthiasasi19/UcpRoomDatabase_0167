package com.example.ucp_2.ui.viewmodel.matakuliah

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp_2.data.entity.Dosen
import com.example.ucp_2.data.entity.MataKuliah
import com.example.ucp_2.repository.dosen.RepositoryDosen
import com.example.ucp_2.repository.matakuliah.LocalRepositoryMk
import com.example.ucp_2.ui.Navigasi.DestinasiUpdateMataKuliah
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateMKViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMataKuliah: LocalRepositoryMk,
    private val repossioryDsn: RepositoryDosen
) : ViewModel(){

    var dosentList by mutableStateOf<List<Dosen>>(emptyList())
        private set

    var updateUiState by mutableStateOf(MataKuliahUiState())
        private set

    private val _kode: String = checkNotNull(savedStateHandle[DestinasiUpdateMataKuliah.kodeMatakuliah])

    init {
        viewModelScope.launch {
            updateUiState = repositoryMataKuliah.getDetailMataKuliah(_kode)
                .filterNotNull()
                .first()
                .toUiStateMataKuliah()
        }
        viewModelScope.launch {
            val dosentListFromRepo = repossioryDsn.getAllDosen().first()
            updateUiState = updateUiState.copy(dosentList = dosentListFromRepo)
        }
    }

    fun MataKuliah.toUiStateMataKuliah(): MataKuliahUiState = MataKuliahUiState(
        mataKuliahEvent = this.toDetailUiEvent()
    )

    fun updateState(mataKuliahEvent: MataKuliahEvent) {
        updateUiState = updateUiState.copy(
            mataKuliahEvent = mataKuliahEvent
        )
    }

    fun validateFields(): Boolean {
        val event = updateUiState.mataKuliahEvent
        val errorState = FormErrorState(
            kode = if (event.kode.isNotEmpty()) null else "Kode tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            sks = if (event.sks.isNotEmpty()) null else "SKS tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "Semester tidak boleh kosong",
            jenis = if (event.jenis.isNotEmpty()) null else "Jenis tidak boleh kosong",
            dosenPengampu = if (event.dosenPengampu.isNotEmpty()) null else "Dosen Pengampu tidak boleh kosong"
        )

        updateUiState = updateUiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun updateData() {
        val currentEvent = updateUiState.mataKuliahEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryMataKuliah.updateMataKuliah(currentEvent.toMataKuliahEntity())
                    updateUiState = updateUiState.copy(
                        snackBarMessage = "Data berhasil diupdate",
                        mataKuliahEvent = MataKuliahEvent(),
                        isEntryValid = FormErrorState()
                    )
                } catch (e: Exception) {
                    updateUiState = updateUiState.copy(
                        snackBarMessage = "Data gagal diupdate: ${e.message}"
                    )
                }
            }
        } else {
            updateUiState = updateUiState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data Anda."
            )
        }
    }

    fun resetSnackBarMessage() {
        updateUiState = updateUiState.copy(snackBarMessage = null)
    }

}