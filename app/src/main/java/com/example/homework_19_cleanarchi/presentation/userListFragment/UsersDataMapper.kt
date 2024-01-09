package com.example.homework_19_cleanarchi.presentation.userListFragment

import com.example.homework_19_cleanarchi.domain.response.UsersResponse

fun UsersResponse.toDomain(): UsersData {
    return UsersData(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        avatar = avatar
    )
}