package com.best.bestbrains.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://reqres.in/api/"


interface ApiRequests {

    @GET(value = "users")
    fun getPage(
        @Query(value = "page")
        page: Int
    ): Call<Pages>

}

 val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(ApiRequests::class.java)


//    val retrofitService : ApiRequests by lazy { retrofit.create(ApiRequests::class.java) }
