package com.example.ucp_2.ui.viewmodel.matakuliah

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp_2.data.entity.MataKuliah
import com.example.ucp_2.repository.matakuliah.RepositoryMataKuliah
import com.example.ucp_2.ui.Navigasi.DestinasiDetailMataKuliah
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailMataKuliahViewModel(
    savedStateHandle: SavedStateHandle,   // Menyimpan data yang terkait dengan lifecycle (digunakan untuk mempertahankan state ViewModel)
    private val RepositoryMataKuliah: RepositoryMataKuliah,
) : ViewModel(){
    private val _kode: String = checkNotNull(savedStateHandle[DestinasiDetailMataKuliah.KODE_MATAKULIAH])

    val detailUiState: StateFlow<DetailUiState> = RepositoryMataKuliah.getDetailMataKuliah(_kode)
        .filterNotNull()
        .map {
            DetailUiState(
                detailUiEvent = it.toDetailUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailUiState(isLoading = true))
            delay(600)  // Menambahkan delay untuk mensimulasikan proses loading
        }
        .catch {
            emit(
                DetailUiState(
                    isLoading = false,   // Menandakan bahwa proses loading selesai meskipun ada error
                    isError = true,
                    erorMessage = it.message ?: "Terjadi Kesalahan",

                    )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailUiState(
                isLoading = true,
            ),
        )

    fun deleteMataKuliah(){  // Fungsi untuk menghapus data mata kuliah
        detailUiState.value.detailUiEvent.toMataKuliahEntity().let {
            viewModelScope.launch {
                RepositoryMataKuliah.deleteMataKuliah(it)  // Memanggil fungsi repository untuk menghapus mata kuliah
            }
        }
    }
}

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