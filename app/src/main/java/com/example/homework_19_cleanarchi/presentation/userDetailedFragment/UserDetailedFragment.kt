package com.example.homework_19_cleanarchi.presentation.userDetailedFragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.homework_19_cleanarchi.data.common.Resource
import com.example.homework_19_cleanarchi.databinding.FragmentUserDetailedBinding
import com.example.homework_19_cleanarchi.presentation.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserDetailedFragment : BaseFragment<FragmentUserDetailedBinding>(FragmentUserDetailedBinding::inflate) {
    private val viewModel: UserDetailedViewModel by viewModels()
    private val userArgs: UserDetailedFragmentArgs by navArgs()

    override fun setUp() {
        getUserInfo()
    }

    override fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.usersDetailedInfo.collect{
                    when(it) {
                        is Resource.Success -> {bindData(it.dataBody)}
                        is Resource.Fail -> {}
                        is Resource.Loading -> {}
                    }
                }
            }
        }
    }

    private fun bindData(userDetailedData: UserDetailedData) {
        with(binding) {
            tvFirstname.text = userDetailedData.data.firstName
            tvLastname.text = userDetailedData.data.lastName
            tvEmail.text = userDetailedData.data.email
            Glide.with(requireContext()).load(userDetailedData.data.avatar).into(ivUserAvatar)
        }
    }

    private fun getUserInfo() {
        val userId = userArgs.userId
        viewModel.getUserDetailedInfo(userId)
    }

}
