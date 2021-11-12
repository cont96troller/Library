package com.cont96roller.library.model

import android.icu.text.CaseMap
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class ResponseMainReview (
    val meta: Meta2,
    @SerializedName("documents") val reviewListModel: MutableList<MainReviewResultModel>
        )

data class Meta2(val is_end: Boolean, val pageable_count: Int, val total_count: Int)

@Parcelize
data class MainReviewResultModel(
    val title: String = "",
    val book: String = "",
    val thumbnail: String = ""
) : Parcelable