package com.cont96roller.library.adapter

import android.content.Context
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cont96roller.library.R
import com.cont96roller.library.SearchBookActivity
import com.cont96roller.library.api.BookService
import com.cont96roller.library.common.LogMsg
import com.cont96roller.library.model.ResponseSearchBook
import com.cont96roller.library.model.SearchBookModel
import com.cont96roller.library.network.RequestToServer
import com.cont96roller.library.network.function.customEnqueue
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Logger

//class SearchBookViewModel(val searchResultAction: SearchBookActivity.SearchAction) : ViewModel() {
class SearchBookViewModel() : ViewModel() {

    private val auth = "KakaoAK 8ff9c8db72481a150a26290fed2ed8a3"
    var responseSearchBook: MutableLiveData<ResponseSearchBook> = MutableLiveData()

    var searchWord: MutableLiveData<String> = MutableLiveData()
   private var resultList = MutableLiveData<List<SearchBookModel>>()
    val searchResult: LiveData<List<SearchBookModel>>
        get() = resultList
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

                responseSearchBook.postValue(response.body())
//                SearchBookActivity.kt observeKakaoBookModel() 가불린다
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
