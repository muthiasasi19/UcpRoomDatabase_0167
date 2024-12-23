package com.example.ucp_2.ui.viewmodel.dosen

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp_2.KrsApp
import com.example.ucp_2.repository.dosen.LocalRepositoryDsn
import com.example.ucp_2.repository.matakuliah.LocalRepositoryMk
import com.example.ucp_2.ui.viewmodel.matakuliah.DetailMataKuliahViewModel
import com.example.ucp_2.ui.viewmodel.matakuliah.HomeMataKuliahViewModel
import com.example.ucp_2.ui.viewmodel.matakuliah.MataKuliahViewModel
import com.example.ucp_2.ui.viewmodel.matakuliah.UpdateMataKuliahViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            DosenViewModel(
                krsApp().containerApp.RepositoryDosen as LocalRepositoryDsn
            )
        }
        initializer {
            HomeDosenViewModel(
                krsApp().containerApp.RepositoryDosen
            )
        }
        initializer {
            DetailDosenViewModel(
                createSavedStateHandle(),
                krsApp().containerApp.RepositoryDosen
            )
        }
        initializer {
            MataKuliahViewModel(
                krsApp().containerApp.RepositoryMataKuliah,
                krsApp().containerApp.RepositoryDosen
            )
        }
        initializer {
            HomeMataKuliahViewModel(
                krsApp().containerApp.RepositoryMataKuliah,
            )
        }
        initializer {
            DetailMataKuliahViewModel(
                createSavedStateHandle(),
                krsApp().containerApp.RepositoryMataKuliah,
            )
        }
        initializer {
            UpdateMataKuliahViewModel(
                createSavedStateHandle(),
                krsApp().containerApp.RepositoryMataKuliah as LocalRepositoryMk,
                krsApp().containerApp.RepositoryDosen as LocalRepositoryDsn,
            )
        }
    }
}


fun CreationExtras.krsApp() : KrsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KrsApp)