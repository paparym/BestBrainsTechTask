package com.best.bestbrains

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.best.bestbrains.api.User
import com.best.bestbrains.databinding.UserItemBinding

class ItemAdapter: PagingDataAdapter<User, ItemAdapter.UserViewHolder>(DiffCallback()) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    class UserViewHolder(private var binding: UserItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.user = user
            binding.executePendingBindings()
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem === newItem

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem.id == newItem.id

    }
}