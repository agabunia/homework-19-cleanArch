package com.example.homework_19_cleanarchi.presentation.userListFragment

data class UsersData(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String,
    var isChecked: Boolean = false
)
