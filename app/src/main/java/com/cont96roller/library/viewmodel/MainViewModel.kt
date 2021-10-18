package com.cont96roller.library.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.cont96roller.library.api.BookService
import com.cont96roller.library.model.KakaoBookModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel(application: Application) : AndroidViewModel(application) {
    var kakaoBookModel: MutableLiveData<KakaoBookModel> = MutableLiveData()

    private val auth = "KakaoAK 8ff9c8db72481a150a26290fed2ed8a3"

    fun getSearchBookResult() {

        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest: Request = chain.request().newBuilder()
                .addHeader("Authorization", "$auth")
                .build()
            chain.proceed(newRequest)
        }.build()


        val retrofit = Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val bookService = retrofit.create(BookService::class.java)


        bookService.getBooksByName(title="s").enqueue(object : Callback<KakaoBookModel> {
            override fun onResponse(call: Call<KakaoBookModel>, response: Response<KakaoBookModel>) {
                LogMsg.e("", "")
                kakaoBookModel.postValue(response.body())
            }

            override fun onFailure(call: Call<KakaoBookModel>, t: Throwable) {
                LogMsg.e("", "${t.message}")
            }

        })

    }


}