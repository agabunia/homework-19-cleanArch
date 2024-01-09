package com.example.homework_19_cleanarchi.data.repository

import com.example.homework_19_cleanarchi.data.common.HandleResponse
import com.example.homework_19_cleanarchi.data.common.Resource
import com.example.homework_19_cleanarchi.data.mapper.toDomain
import com.example.homework_19_cleanarchi.data.service.UserDetailedService
import com.example.homework_19_cleanarchi.domain.repository.UserDetailedRepository
import com.example.homework_19_cleanarchi.domain.response.UserDetailedResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDetailedRepositoryImp @Inject constructor(
    private val userDetailedService: UserDetailedService,
    private val handleResponse: HandleResponse
): UserDetailedRepository {
    override suspend fun getUserDetailed(id: Int): Flow<Resource<UserDetailedResponse>> {
        return handleResponse.safeApiCall {
            userDetailedService.getUserDetailed(id = id)
        }.map {
            when(it) {
                is Resource.Success -> Resource.Success(dataBody = it.dataBody.toDomain())
                is Resource.Fail -> Resource.Fail(errorMessage = it.errorMessage)
                is Resource.Loading -> Resource.Loading(isLoading = it.isLoading)
            }
        }
    }
}
