package com.best.bestbrains

import androidx.lifecycle.*
import androidx.paging.PagingData
import com.best.bestbrains.api.User
import kotlinx.coroutines.flow.Flow

class ListViewModel(private val repository: UserRepository): ViewModel() {

    private val currentUser = MutableLiveData(repository.getResult())
    val users = repository.getResult()

}