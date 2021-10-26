package com.cont96roller.library.network.function

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


fun <ResponseType> Call<ResponseType>.customEnqueue(
    onFail: () -> Unit,
    onSuccess: (ResponseType) -> Unit,
    onError: (Response<ResponseType>) -> Unit = {}
) {
    this.enqueue(object : Callback<ResponseType> {
        override fun onFailure(call: Call<ResponseType>, t: Throwable) {
            onFail()
        }

        override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>) {
            response.body()?.let {
                onSuccess(it)
            } ?: onError(response)
        }
    })
}


