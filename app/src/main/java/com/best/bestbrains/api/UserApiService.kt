package com.best.bestbrains.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiRequests {

    companion object {
        const val BASE_URL = "https://reqres.in/api/"
    }

    @GET(value = "users")
    suspend fun getPage(
        @Query(value = "page")
        page: Int
    ): Pages

}

