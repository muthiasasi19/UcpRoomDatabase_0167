package com.example.ucp_2.ui.viewmodel.dosen

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp_2.KuliahApp
import com.example.ucp_2.repository.dosen.LocalRepositoryDsn
import com.example.ucp_2.repository.matakuliah.LocalRepositoryMk
import com.example.ucp_2.ui.viewmodel.matakuliah.DetailMKViewModel
import com.example.ucp_2.ui.viewmodel.matakuliah.HomeMKViewModel
import com.example.ucp_2.ui.viewmodel.matakuliah.MKViewModel
import com.example.ucp_2.ui.viewmodel.matakuliah.UpdateMKViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            DosenViewModel(
                kuliahApp().containerApp.RepositoryDosen as LocalRepositoryDsn
            )
        }
        initializer {
            HomeDosenViewModel(
                kuliahApp().containerApp.RepositoryDosen
            )
        }

        initializer {
            MKViewModel(
                kuliahApp().containerApp.RepositoryMataKuliah,
                kuliahApp().containerApp.RepositoryDosen
            )
        }
        initializer {
            HomeMKViewModel(
                kuliahApp().containerApp.RepositoryMataKuliah,
            )
        }
        initializer {
            DetailMKViewModel(
                createSavedStateHandle(),
                kuliahApp().containerApp.RepositoryMataKuliah,
            )
        }
        initializer {
            UpdateMKViewModel(
                createSavedStateHandle(),
                kuliahApp().containerApp.RepositoryMataKuliah as LocalRepositoryMk,
                kuliahApp().containerApp.RepositoryDosen as LocalRepositoryDsn,
            )
        }
    }
}


fun CreationExtras.kuliahApp() : KuliahApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KuliahApp)