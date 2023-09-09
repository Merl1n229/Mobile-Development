package com.example.project.data

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.project.domain.MainRepository
import javax.inject.Inject

class NotificationWorkerFactory @Inject constructor(
    private val mainRepository: MainRepository
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return NotificationWorker(
            appContext,
            workerParameters,
            mainRepository
        )
    }
}