package com.example.project.presentation.Main

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.project.data.NotificationWorker
import com.example.project.domain.MainRepository
import com.example.project.domain.NotifyItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val application: Application,
    private val mainRepository: MainRepository
): ViewModel() {



    fun getNotifications() : LiveData<List<NotifyItem>> = mainRepository.getNotifications()


    fun notificationFromArduino(){
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            NotificationWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            NotificationWorker.makeRequest()
        )
    }


}