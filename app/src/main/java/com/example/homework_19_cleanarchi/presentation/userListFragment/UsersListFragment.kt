package com.example.homework_19_cleanarchi.presentation.userListFragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_19_cleanarchi.data.common.Resource
import com.example.homework_19_cleanarchi.databinding.FragmentUsersListBinding
import com.example.homework_19_cleanarchi.presentation.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UsersListFragment :
    BaseFragment<FragmentUsersListBinding>(FragmentUsersListBinding::inflate) {
    private val viewModel: UsersViewModel by viewModels()
    private lateinit var userListAdapter: UsersRecyclerAdapter

    override fun setUp() {
        setUsersListAdapter()
        getUsersData()
    }

    override fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.usersFlow.collect{
                    when(it) {
                        is Resource.Success -> {userListAdapter.submitList(it.dataBody)}
                        is Resource.Fail -> {}
                        is Resource.Loading -> {}
                    }
                }
            }
        }
    }

    private fun setUsersListAdapter() {
        userListAdapter = UsersRecyclerAdapter()
        with(binding) {
            usersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            usersRecyclerView.adapter = userListAdapter
        }
        userListAdapter.onUserClick = {
            getUserId(it.id)
        }
    }

    private fun getUsersData() {
        viewModel.getUsers()
    }

    private fun getUserId(id: Int) {
        val action = UsersListFragmentDirections.actionUsersListFragmentToUserDetailedFragment(id)
        Navigation.findNavController(binding.root).navigate(action)
    }

}
