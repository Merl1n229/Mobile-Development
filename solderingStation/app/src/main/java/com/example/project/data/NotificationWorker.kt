package com.example.project.data

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.project.R
import com.example.project.domain.MainRepository
import com.example.project.domain.NotifyItem
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@HiltWorker
class NotificationWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workerParameters: WorkerParameters,
    @Assisted  private val mainRepository: MainRepository
) : CoroutineWorker(context, workerParameters) {



    override suspend fun doWork(): Result {
        while(true){
            val notifyItem = getArduino()
            if (notifyItem.text != ""){
                mainRepository.addNotification(notifyItem)
//                showNotification(notifyItem.text)
            }

            delay(10000)
        }
    }

    fun getArduino() : NotifyItem {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val timeFormat = SimpleDateFormat("HH:mm:ss")

        val currentDate = Calendar.getInstance().time
        val dateString = dateFormat.format(currentDate)
        val timeString = timeFormat.format(currentDate)

        return NotifyItem(dateString, timeString, "Notification")
    }

    private fun showNotification(text: String) {
        val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Уведомление")
            .setContentText(text)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)


        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }


    companion object {
        const val NOTIFICATION_ID = 101
        const val CHANNEL_ID = "channelID"
        private const val CHANNEL_NAME = "channel_name"
        const val NAME = "NotificationWorker"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<NotificationWorker>().build()
        }
    }
}