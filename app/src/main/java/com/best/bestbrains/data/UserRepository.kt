package com.best.bestbrains.data
import androidx.paging.*
import com.best.bestbrains.api.ApiRequests
import com.best.bestbrains.data.UserPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val apiRequests: ApiRequests) {

    // Paging 3 library Util
    fun getResult() = Pager(
            config = PagingConfig(
                pageSize = 6,
                maxSize = 12,
                enablePlaceholders = false,
                // for presentation purposes
                prefetchDistance = 1

            ),
            pagingSourceFactory = { UserPagingSource(apiRequests) }
        ).liveData

}