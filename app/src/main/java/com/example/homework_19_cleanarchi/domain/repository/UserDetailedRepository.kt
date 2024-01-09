package com.example.homework_19_cleanarchi.domain.repository

import com.example.homework_19_cleanarchi.data.common.Resource
import com.example.homework_19_cleanarchi.domain.response.UserDetailedResponse
import kotlinx.coroutines.flow.Flow

interface UserDetailedRepository {
    suspend fun getUserDetailed(id: Int): Flow<Resource<UserDetailedResponse>>
}
