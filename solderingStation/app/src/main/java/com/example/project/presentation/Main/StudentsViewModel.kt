package com.example.project.presentation.Main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.project.domain.MainRepository
import com.example.project.domain.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class StudentsViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    fun getStudents(): LiveData<List<User>> {
        return mainRepository.getStudents()
    }
}