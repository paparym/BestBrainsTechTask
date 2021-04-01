package com.best.bestbrains.data

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.paging.PagingSource
import com.best.bestbrains.api.ApiRequests
import com.best.bestbrains.api.User
import retrofit2.awaitResponse


// Paging 3 library util
class UserPagingSource(private val apiRequests: ApiRequests): PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val position = params.key ?: 1
        return try {
            val response = apiRequests.getPage(position)
            // for debug
            Log.d("TAG", "${response.data.size} items loaded.")

            LoadResult.Page(
                data = response.data,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (response.data.isEmpty()) null else position + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}