package com.best.bestbrains.fragments.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.best.bestbrains.api.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class DetailViewModel constructor(private val user: User): ViewModel() {


    private val _userItem = MutableLiveData<User>()
    val userItem: LiveData<User>
        get() = _userItem


    init {
        _userItem.value = user
    }
}