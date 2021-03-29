package com.best.bestbrains.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://reqres.in/api/"

data class UserResponse(val results: List<User>)

interface ApiRequests {

    @GET(value = "users")
    suspend fun getPage(
        @Query(value = "page")
        page: Int
    ): UserResponse

}

 val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(ApiRequests::class.java)


//    val retrofitService : ApiRequests by lazy { retrofit.create(ApiRequests::class.java) }
