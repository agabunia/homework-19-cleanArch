package com.example.homework_19_cleanarchi.data.service

import com.example.homework_19_cleanarchi.data.model.UserDetailedDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserDetailedService {
    @GET("https://reqres.in/api/users/{id}")
    suspend fun getUserDetailed(@Path("id") id: Int): Response<UserDetailedDto>
}
