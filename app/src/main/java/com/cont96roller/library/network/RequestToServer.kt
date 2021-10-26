package com.cont96roller.library.network

import android.provider.SyncStateContract
import com.cont96roller.library.`interface`.RequestSearchBookInterface
import com.cont96roller.library.common.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RequestToServer {

    var kakaoRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.KAKAO_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var kakaoService: RequestSearchBookInterface = kakaoRetrofit.create(RequestSearchBookInterface::class.java)


}