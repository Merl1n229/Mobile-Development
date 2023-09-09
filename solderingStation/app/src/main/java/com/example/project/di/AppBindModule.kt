package com.example.project.di

import com.example.project.data.AuthRepositoryImpl
import com.example.project.data.MainRepositoryImpl
import com.example.project.domain.AuthRepository
import com.example.project.domain.MainRepository
import dagger.Binds


import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface AppBindModule {
    @Binds
    fun bindAuthRepository(authImpl: AuthRepositoryImpl) : AuthRepository

    @Binds
    fun bindMainRepository(authImpl: MainRepositoryImpl) : MainRepository
}