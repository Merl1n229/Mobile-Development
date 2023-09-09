package com.example.project.presentation

import android.app.Application
import androidx.work.Configuration
import com.example.project.data.NotificationWorkerFactory


import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class MainApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: NotificationWorkerFactory


    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }

}