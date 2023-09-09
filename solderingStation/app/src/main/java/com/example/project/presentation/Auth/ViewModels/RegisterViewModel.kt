package com.example.project.presentation.Auth.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.domain.AuthRepository
import com.example.project.domain.AuthState
import com.example.project.domain.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {



    private val _stateFlow = MutableStateFlow<AuthState>((AuthState.Loading()))
    var stateFlow = _stateFlow.asStateFlow()

    fun register(email: String, password: String, name: String, surName: String, type: String){

        authRepository.register(email, password, User(name=name, surName = surName,type = type)).onEach {
            _stateFlow.value = it
        }.launchIn(viewModelScope)
    }
}