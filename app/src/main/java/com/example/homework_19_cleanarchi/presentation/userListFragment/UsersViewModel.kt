package com.example.homework_19_cleanarchi.presentation.userListFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_19_cleanarchi.data.common.Resource
import com.example.homework_19_cleanarchi.domain.repository.UsersRepository
import com.example.homework_19_cleanarchi.domain.response.UsersResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val repository: UsersRepository) : ViewModel() {
    private val _usersFlow =
        MutableStateFlow<Resource<List<UsersData>>>(Resource.Success(dataBody = emptyList()))
    val usersFlow: SharedFlow<Resource<List<UsersData>>> = _usersFlow.asStateFlow()

    fun getUsers() {
        viewModelScope.launch {
            repository.getUsers().collect {
                when (it) {
                    is Resource.Success -> {
                        _usersFlow.value = Resource.Success(dataBody = it.data!!.map {
                            it.toDomain()
                        })
                    }

                    is Resource.Fail -> {
                        _usersFlow.value = Resource.Fail(errorMessage = it.errorMessage)
                    }

                    is Resource.Loading -> {
                        _usersFlow.value = Resource.Loading(isLoading = it.loading)
                    }
                }
            }
        }
    }
}
