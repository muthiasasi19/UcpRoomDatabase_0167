package com.example.ucp_2.dependenciesinjection

import android.content.Context
import com.example.ucp_2.data.database.KuliahDatabase
import com.example.ucp_2.repository.dosen.LocalRepositoryDsn
import com.example.ucp_2.repository.dosen.RepositoryDosen
import com.example.ucp_2.repository.matakuliah.LocalRepositoryMk
import com.example.ucp_2.repository.matakuliah.RepositoryMataKuliah

interface InterfaceContainerApp {
    val RepositoryDosen: RepositoryDosen
    val RepositoryMataKuliah: RepositoryMataKuliah

}

class ContainerApp(private val context: Context) : InterfaceContainerApp {
    override val RepositoryDosen: RepositoryDosen by lazy {
        LocalRepositoryDsn(KuliahDatabase.getDatabase(context).dosenDao())
    }

    override val RepositoryMataKuliah: RepositoryMataKuliah by lazy {
        LocalRepositoryMk(KuliahDatabase.getDatabase(context).mataKuliahDao())
    }
}
