package com.cont96roller.library.api

import com.cont96roller.library.model.KakaoBookModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BookService {
    @GET("v3/search/book")
    fun getBooksByName(
        @Header("Authorization") token: String = "KakaoAK 8ff9c8db72481a150a26290fed2ed8a3",
        @Query("query") title: String
    ): Call<KakaoBookModel>

}