package com.example.project.domain

import androidx.lifecycle.LiveData

interface MainRepository {
    fun getStudents() : LiveData<List<User>>

    fun addNotification(notification: NotifyItem)

    fun getNotifications(): LiveData<List<NotifyItem>>
}