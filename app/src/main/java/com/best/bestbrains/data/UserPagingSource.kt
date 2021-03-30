package com.best.bestbrains.data

import android.util.Log
import androidx.paging.PagingSource
import com.best.bestbrains.api.ApiRequests
import com.best.bestbrains.api.User
import retrofit2.awaitResponse


// Paging 3 library util
class UserPagingSource(private val apiRequests: ApiRequests): PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val position = params.key ?: 1
        return try {
            val response = apiRequests.getPage(position).awaitResponse()
            val result = response.body()
            // for debug
            Log.d("TAG", "${result!!.data.size} items loaded.")

            LoadResult.Page(
                data = result.data,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (result.data.isEmpty()) null else position + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}