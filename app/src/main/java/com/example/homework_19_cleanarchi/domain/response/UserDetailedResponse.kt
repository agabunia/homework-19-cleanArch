package com.example.homework_19_cleanarchi.domain.response

data class UserDetailedResponse(
    val data: Detailed,
) {
    data class Detailed(
        val id: Int,
        val email: String,
        val firstName: String,
        val lastName: String,
        val avatar: String
    )
}
