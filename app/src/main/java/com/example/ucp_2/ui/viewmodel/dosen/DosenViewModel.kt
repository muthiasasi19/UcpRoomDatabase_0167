package com.example.ucp_2.ui.viewmodel.dosen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp_2.data.entity.Dosen
import com.example.ucp_2.repository.dosen.LocalRepositoryDsn
import kotlinx.coroutines.launch

class DosenViewModel(private val repositoryDosen: LocalRepositoryDsn): ViewModel() { //Mendeklarasikan kelas DosenViewMode
    var uiState by mutableStateOf(DosenUiState())

    //untuk memperbarui state uiState dengan event dosen yang baru
    fun updateState(dosenEvent: DosenEvent){
        uiState = uiState.copy(
            dosenEvent = dosenEvent
        )
    }

    //memvalidasi apakah inputan dari pengguna sudah sesuai dan tidak kosong
    private fun validateFields(): Boolean {
        val event = uiState.dosenEvent
        val errorState = FormErrorState(
            nidn = if (event.nidn.isNotEmpty()) null else "NIDN tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            jenisKelamin = if (event.jenisKelamin.isNotEmpty()) null else "Jenis Kelamin tidak boleh kosong"
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData() {
        val currentEvent = uiState.dosenEvent
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryDosen.insertDosen(currentEvent.toDosenEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data dosen berhasil disimpan",
                        dosenEvent = DosenEvent(),
                        isEntryValid = FormErrorState()
                    )
                } catch (e: Exception) {
                    uiState = uiState.copy(
                        snackBarMessage = "Data dosen gagal disimpan: ${e.message}"
                    )
                }
            }
        } else {
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data Anda"
            )
        }
    }

    //untuk mereset pesan snackbar setelah ditampilkan.
    fun resetSnackBarMessage() {
        uiState = uiState.copy(snackBarMessage = null) //Menghapus pesan snackbar dengan mengatur nilai snackBarMessage menjadi null.
    }
}

data class DosenEvent(
    val nidn: String = "",
    val nama: String = "",
    val jenisKelamin: String = ""
) {

}
fun DosenEvent.toDosenEntity(): Dosen {
    return Dosen(
        nidn = this.nidn,
        nama = this.nama,
        jenisKelamin = this.jenisKelamin
    )
}
data class DosenUiState(
    val dosenEvent: DosenEvent = DosenEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)

data class FormErrorState(
    val nidn: String? = null,
    val nama: String? = null,
    val jenisKelamin: String? = null
) {
    fun isValid(): Boolean {
        return nidn == null && nama == null && jenisKelamin == null
    }
}