package com.example.homework_19_cleanarchi.presentation.userListFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework_19_cleanarchi.databinding.UserLayoutBinding
import com.example.homework_19_cleanarchi.domain.response.UsersResponse

class UsersRecyclerAdapter :
    ListAdapter<UsersData, UsersRecyclerAdapter.UsersViewHolder>(UserCallback()) {

    class UserCallback : DiffUtil.ItemCallback<UsersData>() {
        override fun areItemsTheSame(oldItem: UsersData, newItem: UsersData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UsersData, newItem: UsersData): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return UsersViewHolder(UserLayoutBinding.inflate(inflate, parent, false))
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val currentUser = getItem(position)
        holder.bind(currentUser)
    }

    var onUserClick: ((UsersData) -> Unit)? = null

    inner class UsersViewHolder(private val binding: UserLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val context = binding.root.context
        fun bind(user: UsersData) {
            with(binding) {
                tvFirstname.text = user.firstName
                tvLastname.text = user.lastName
                tvEmail.text = user.email
                Glide.with(context).load(user.avatar).into(ivUserAvatar)
                ivUserAvatar.setOnClickListener {
                    onUserClick?.invoke(currentList[adapterPosition])
                }
            }
        }
    }
}
