package com.best.bestbrains.fragments.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.best.bestbrains.api.User
import com.best.bestbrains.data.UserRepository
import com.best.bestbrains.fragments.list.ListViewModel

class DetailViewModelFactory(
    private val user: User,
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(user) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
