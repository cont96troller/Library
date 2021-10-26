package com.cont96roller.library.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.cont96roller.library.api.BookService
import com.cont96roller.library.common.LogMsg
import com.cont96roller.library.model.ResponseSearchBook
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    var responseSearchBook: MutableLiveData<ResponseSearchBook> = MutableLiveData()

    private val auth = "KakaoAK 8ff9c8db72481a150a26290fed2ed8a3"

    fun getSearchBookResult(keyword: String) {

        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest: Request = chain.request().newBuilder()
                .addHeader("Authorization", "$auth")
                .build()
            chain.proceed((newRequest))
        }.build()


        val retrofit = Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val bookService = retrofit.create(BookService::class.java)

        bookService.getBooksByName(title = keyword).enqueue(object : Callback<ResponseSearchBook> {
            override fun onResponse(
                call: Call<ResponseSearchBook>,
                response: Response<ResponseSearchBook>
            ) {
                LogMsg.e("", "")
                responseSearchBook.postValue(response.body())
            }

            override fun onFailure(call: Call<ResponseSearchBook>, t: Throwable) {
                LogMsg.e("", "${t.message}")
            }

        })
    }
}