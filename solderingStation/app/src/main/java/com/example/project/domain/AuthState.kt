package com.example.project.domain

sealed class AuthState {
    class Success(val data: User?=null): AuthState()
    class Error(val message :String? = null): AuthState()
    class Loading: AuthState()
}