package com.example.homework_19_cleanarchi.presentation.userDetailedFragment

import com.example.homework_19_cleanarchi.domain.response.UserDetailedResponse

fun UserDetailedResponse.toDomain(): UserDetailedData {
    return UserDetailedData(
        UserDetailedData.Detailed(
            id = this.data.id,
            email = this.data.email,
            firstName = this.data.firstName,
            lastName = this.data.lastName,
            avatar = this.data.avatar
        )
    )
}
