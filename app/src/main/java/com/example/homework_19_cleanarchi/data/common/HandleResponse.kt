package com.example.homework_19_cleanarchi.data.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class HandleResponse @Inject constructor() {
    suspend fun <T:Any> safeApiCall(call: suspend () -> Response<T>): Flow<Resource<T>> = flow {
        emit(Resource.Loading(isLoading = true))
        try {
            val response: Response<T> = call()
            if(response.isSuccessful) {
                emit(Resource.Success(dataBody = response.body()!!))
            } else {
                emit(Resource.Fail(errorMessage = response.errorBody()?.toString() ?: ""))
            }
        } catch (e: Throwable) {
            emit(Resource.Fail(errorMessage = e.message ?: ""))
        }
        emit(Resource.Loading(isLoading = false))
    }
}
