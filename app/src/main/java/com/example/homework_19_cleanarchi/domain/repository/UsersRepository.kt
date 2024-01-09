package com.example.homework_19_cleanarchi.domain.repository

import com.example.homework_19_cleanarchi.data.common.Resource
import com.example.homework_19_cleanarchi.domain.response.UsersResponse
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun getUsers(): Flow<Resource<List<UsersResponse>>>
}
