package com.example.homework_19_cleanarchi.di

import com.example.homework_19_cleanarchi.data.common.HandleResponse
import com.example.homework_19_cleanarchi.data.repository.UserDetailedRepositoryImp
import com.example.homework_19_cleanarchi.data.repository.UsersRepositoryImp
import com.example.homework_19_cleanarchi.data.service.UserDetailedService
import com.example.homework_19_cleanarchi.data.service.UsersService
import com.example.homework_19_cleanarchi.domain.repository.UserDetailedRepository
import com.example.homework_19_cleanarchi.domain.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUsersRepository(
        usersService: UsersService,
        handleResponse: HandleResponse
    ): UsersRepository {
        return UsersRepositoryImp(usersService = usersService, handleResponse = handleResponse)
    }

    @Singleton
    @Provides
    fun provideUserDetailedRepository(
        userDetailedService: UserDetailedService,
        handleResponse: HandleResponse
    ): UserDetailedRepository {
        return UserDetailedRepositoryImp(
            userDetailedService = userDetailedService,
            handleResponse = handleResponse
        )
    }
}
