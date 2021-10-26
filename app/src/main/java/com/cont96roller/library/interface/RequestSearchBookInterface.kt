package com.cont96roller.library.`interface`

import com.cont96roller.library.model.ResponseSearchBook
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RequestSearchBookInterface {
    @GET("v3/search/book")
    fun searchBook(
        @Header("Authorization") token: String,
        @Query("query") title: String
    ): Call<ResponseSearchBook>
}