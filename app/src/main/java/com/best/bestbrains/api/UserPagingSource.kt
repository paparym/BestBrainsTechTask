package com.best.bestbrains.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.awaitResponse

class UserPagingSource(private val apiRequests: ApiRequests): PagingSource<Int, User>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val position = 1
        val response = apiRequests.getPage(position)
        val result = response.results

        return try {
            LoadResult.Page(
                data = result,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (result.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}