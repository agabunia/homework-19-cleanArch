package com.example.homework_19_cleanarchi.data.mapper

import com.example.homework_19_cleanarchi.data.model.UsersDto
import com.example.homework_19_cleanarchi.domain.response.UsersResponse

fun UsersDto.toDomain(): UsersResponse {
    return UsersResponse(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}
