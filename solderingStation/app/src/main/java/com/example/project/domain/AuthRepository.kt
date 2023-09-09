package com.example.project.domain

import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun register(email: String, password: String, user: User) : Flow<AuthState>

    fun login(email: String, password: String) : Flow<AuthState>

    fun resetPassword(email: String) : Flow<AuthState>
}