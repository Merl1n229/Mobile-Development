package com.example.project.presentation.Auth.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.domain.AuthRepository
import com.example.project.domain.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val authRepository: AuthRepository
)  : ViewModel() {



    private val _stateFlow = MutableStateFlow<AuthState>(AuthState.Loading())
    var stateFlow = _stateFlow.asStateFlow()

    fun resetPassword(email: String){
        authRepository.resetPassword(email).onEach {
            _stateFlow.value = it
        }.launchIn(viewModelScope)
    }
}