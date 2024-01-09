package com.example.homework_19_cleanarchi.di

import com.example.homework_19_cleanarchi.data.common.HandleResponse
import com.example.homework_19_cleanarchi.data.service.UserDetailedService
import com.example.homework_19_cleanarchi.data.service.UsersService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModel {
    private const val BASE_URL = "https://reqres.in/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun provideUsersService(retrofit: Retrofit): UsersService {
        return retrofit.create(UsersService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserDetailedService(retrofit: Retrofit): UserDetailedService {
        return retrofit.create(UserDetailedService::class.java)
    }
}
