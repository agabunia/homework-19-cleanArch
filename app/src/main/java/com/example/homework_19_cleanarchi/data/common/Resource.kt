package com.example.homework_19_cleanarchi.data.common

sealed class Resource<T>(
    val data: T? = null,
    val error: String? = null,
    val loading: Boolean = false
) {
    data class Success<T>(val dataBody: T) : Resource<T>(data = dataBody)
    data class Fail<T>(val errorMessage: String) : Resource<T>(error = errorMessage)
    data class Loading<T>(val isLoading: Boolean) : Resource<T>(loading = isLoading)
}
