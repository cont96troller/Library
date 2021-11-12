package com.cont96roller.library.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class ResponseSearchBook(
    val meta: Meta,
    @SerializedName("documents") val bookListModel: MutableList<SearchBookResultModel>
)

data class Meta(val is_end: Boolean, val pageable_count: Int, val total_count: Int)

@Parcelize
data class SearchBookResultModel(
    val authors: Array<String>,
    val contents: String = "",
    val dataTime: String = "",
    val isbn: String = "",
    val publisher: String = "",
    val thumbnail: String = "",
    val title: String = "",
    val url: String = ""
) : Parcelable




