package com.example.ucp_2.dependenciesinjection

import android.content.Context
import com.example.ucp_2.data.database.kuliah
import com.example.ucp_2.repository.matakuliah.LocalRepositoryMK
import com.example.ucp_2.repository.matakuliah.RepositoryMK

// repository berada di sini (ContainerApp)
interface InterfaceContainerApp {
    val repositoryMK : RepositoryMK
}

class ContainerApp(private val context: Context) : InterfaceContainerApp {
    override val repositoryMK: RepositoryMK by lazy {
        LocalRepositoryMK(kuliah.getDatabase(context).matakuliahDao())
    }
}

