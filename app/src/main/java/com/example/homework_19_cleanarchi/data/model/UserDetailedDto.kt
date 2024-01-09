package com.example.homework_19_cleanarchi.data.model

import com.squareup.moshi.Json

data class UserDetailedDto(
    @Json(name = "data")
    val data: Detailed,
) {
    data class Detailed(
        @Json(name = "id")
        val id: Int,
        @Json(name = "email")
        val email: String,
        @Json(name = "first_name")
        val firstName: String,
        @Json(name = "last_name")
        val lastName: String,
        @Json(name = "avatar")
        val avatar: String
    )
}
