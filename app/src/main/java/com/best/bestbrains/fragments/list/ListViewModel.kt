package com.best.bestbrains.fragments.list

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.best.bestbrains.api.User
import com.best.bestbrains.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: UserRepository): ViewModel() {

    // LiveData to be observed
    // Should be cached for not being triggered each time
    val users = repository.getResult().cachedIn(viewModelScope)

    private val _itemDetail = MutableLiveData<User>()
    val itemDetail: LiveData<User>
        get() = _itemDetail

    // Navigation purposes
    fun navigateToDetail(user: User) {
        _itemDetail.value = user
    }

    // We're done navigating
    fun navigateCompleted() {
        _itemDetail.value = null
    }

}