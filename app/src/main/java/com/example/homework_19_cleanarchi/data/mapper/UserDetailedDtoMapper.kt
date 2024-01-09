package com.example.homework_19_cleanarchi.data.mapper

import com.example.homework_19_cleanarchi.data.model.UserDetailedDto
import com.example.homework_19_cleanarchi.domain.response.UserDetailedResponse

fun UserDetailedDto.toDomain(): UserDetailedResponse {
    return UserDetailedResponse(
        UserDetailedResponse.Detailed(
            id = this.data.id,
            email = this.data.email,
            firstName = this.data.firstName,
            lastName = this.data.lastName,
            avatar = this.data.avatar
        )
    )
}
