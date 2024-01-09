package com.example.homework_19_cleanarchi.data.service

import com.example.homework_19_cleanarchi.data.model.UsersDto
import retrofit2.Response
import retrofit2.http.GET

interface UsersService {
    @GET("https://run.mocky.io/v3/7ec14eae-06bf-4f6d-86d2-ac1b9df5fe3d")
    suspend fun getUsers(): Response<List<UsersDto>>
}
