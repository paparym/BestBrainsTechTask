package com.best.bestbrains

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.best.bestbrains.api.ApiRequests
import com.best.bestbrains.api.User
import com.best.bestbrains.api.UserPagingSource
import kotlinx.coroutines.flow.Flow

class UserRepository(private val apiRequests: ApiRequests) {


    fun getResult() = Pager(
            config = PagingConfig(
                pageSize = 6,
                maxSize = 12,
                enablePlaceholders = false,
                prefetchDistance = 1

            ),
            pagingSourceFactory = { UserPagingSource(apiRequests) }
        ).liveData

}