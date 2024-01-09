package com.example.homework_19_cleanarchi.data.repository

import com.example.homework_19_cleanarchi.data.common.HandleResponse
import com.example.homework_19_cleanarchi.data.common.Resource
import com.example.homework_19_cleanarchi.data.mapper.toDomain
import com.example.homework_19_cleanarchi.data.service.UsersService
import com.example.homework_19_cleanarchi.domain.repository.UsersRepository
import com.example.homework_19_cleanarchi.domain.response.UsersResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UsersRepositoryImp @Inject constructor(
    private val usersService: UsersService,
    private val handleResponse: HandleResponse
): UsersRepository {
    override suspend fun getUsers(): Flow<Resource<List<UsersResponse>>> {
        return handleResponse.safeApiCall {
            usersService.getUsers()
        }.map {
            when(it) {
                is Resource.Success -> Resource.Success(dataBody = it.dataBody.map {
                    it.toDomain()
                })
                is Resource.Fail -> Resource.Fail(errorMessage = it.errorMessage)
                is Resource.Loading -> Resource.Loading(isLoading = it.isLoading)
            }
        }
    }
}
