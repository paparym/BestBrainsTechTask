package com.best.bestbrains

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.best.bestbrains.api.ApiRequests
import com.best.bestbrains.api.User
import com.best.bestbrains.api.UserPagingSource
import kotlinx.coroutines.flow.Flow

class UserRepository(private val apiRequests: ApiRequests) {

    val usersTest: Flow<PagingData<User>> = Pager(PagingConfig(pageSize = 20)) {
        UserPagingSource(apiRequests)
    }.flow

    fun getResult() = Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false,

            ),
            pagingSourceFactory = { UserPagingSource(apiRequests) }
        ).flow

}