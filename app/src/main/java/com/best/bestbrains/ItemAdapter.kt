package com.best.bestbrains

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.best.bestbrains.api.User
import com.best.bestbrains.databinding.UserItemBinding

// Paging adapter for Paging 3 library
class ItemAdapter(val onClickListener: CustomClickListener): PagingDataAdapter<User, ItemAdapter.UserViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), parent.context)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.itemView.setOnClickListener {
            // Custom onClickListener
            onClickListener.onClick(currentItem!!)
        }
        holder.bind(currentItem!!)

    }

    class UserViewHolder(private var binding: UserItemBinding, private val context: Context): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.user = user
            // Only Glide
            binding.executePendingBindings()
            // Simple animation
            val animation = AnimationUtils.loadAnimation(context, R.anim.right_to_left)
            itemView.animation = animation
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem === newItem

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem.id == newItem.id

    }

    // We implement a custom onClickListener in order to preserve the separation of concerns correctly.
    // However, it may be replaced with simple onClickListener in ViewHolder class or onBindViewHolder method.
    class CustomClickListener(val clickListener: (user: User) -> Unit) {
        fun onClick(user: User) = clickListener(user)
    }
}