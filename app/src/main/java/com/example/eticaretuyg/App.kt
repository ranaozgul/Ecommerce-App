package com.example.eticaretuyg

import android.app.Application

class App : Application() {
    companion object {
        lateinit var instance: App
            private set
        val favoriUrunDao by lazy {
            UygDatabase.getDatabase(instance).favoriUrunDao()
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
