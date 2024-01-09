package com.example.homework_19_cleanarchi.presentation.userDetailedFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_19_cleanarchi.data.common.Resource
import com.example.homework_19_cleanarchi.domain.repository.UserDetailedRepository
import com.example.homework_19_cleanarchi.domain.response.UserDetailedResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailedViewModel @Inject constructor(private var repository: UserDetailedRepository) :
    ViewModel() {
    private val _usersDetailedInfo = MutableStateFlow<Resource<UserDetailedData>>(
        Resource.Success(
            dataBody = UserDetailedData(data = UserDetailedData.Detailed(0, "", "", "", ""))
        )
    )
    val usersDetailedInfo: SharedFlow<Resource<UserDetailedData>> =
        _usersDetailedInfo.asStateFlow()

    fun getUserDetailedInfo(userId: Int) {
        viewModelScope.launch {
            repository.getUserDetailed(userId).collect{
                when (it) {
                    is Resource.Success -> {
                        _usersDetailedInfo.value = Resource.Success(dataBody = it.data!!.toDomain())
                    }

                    is Resource.Fail -> {
                        _usersDetailedInfo.value = Resource.Fail(errorMessage = it.errorMessage)
                    }

                    is Resource.Loading -> {
                        _usersDetailedInfo.value = Resource.Loading(isLoading = it.loading)
                    }
                }
            }
        }
    }

}
