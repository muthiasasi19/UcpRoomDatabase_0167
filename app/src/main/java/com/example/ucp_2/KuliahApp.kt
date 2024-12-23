package com.example.ucp_2

import android.app.Application
import com.example.ucp_2.dependenciesinjection.ContainerApp


class KuliahApp : Application() {
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this) // Membuat instace(pembuat object)
        //Instance adalah object yang dibuat dari class
    }
}