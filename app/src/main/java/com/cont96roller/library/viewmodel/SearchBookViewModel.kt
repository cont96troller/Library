package com.cont96roller.library.viewmodel

import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cont96roller.library.api.BookService
import com.cont96roller.library.common.LogMsg
import com.cont96roller.library.model.SearchBookResultModel
import com.cont96roller.library.model.ResponseSearchBook
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchBookViewModel : ViewModel() {

    private val auth = "KakaoAK 8ff9c8db72481a150a26290fed2ed8a3"
    var responseSearchBook: MutableLiveData<ResponseSearchBook> = MutableLiveData()

    var searchWord: MutableLiveData<String> = MutableLiveData()

    private var _resultList = MutableLiveData<List<SearchBookResultModel>>()
    val searchResultModel: LiveData<List<SearchBookResultModel>>
        get() = _resultList
/*
    val editorActionListener : TextView.OnEditorActionListener


    init {
        editorActionListener =
            TextView.OnEditorActionListener{ txtView, actionId, event ->
                return@OnEditorActionListener if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    txtView.run {
                    }
                    true
                } else {
                    false
                }
            }
    }*/

    fun searchBook(keyword: String) {

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


        bookService.getBooksByName(title = keyword).enqueue(object : Callback<ResponseSearchBook> {
            override fun onResponse(
                call: Call<ResponseSearchBook>,
                response: Response<ResponseSearchBook>
            ) {
                LogMsg.e("", "")
                val result = response.body()

                responseSearchBook.postValue(result)
                _resultList.postValue(result?.bookListModel)

            }

            override fun onFailure(call: Call<ResponseSearchBook>, t: Throwable) {
                LogMsg.e("", "${t.message}")
            }

        })

    }

    @BindingAdapter("onEditorActionListener")
    fun setOnEditorActionListener(
        editText: AppCompatEditText, editorActionListener: TextView.OnEditorActionListener
    ) {
        editText.setOnEditorActionListener(editorActionListener)
    }
}
