package com.cont96roller.library.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchBookModel (
    val title: String = "",
    val author: String = "",
    val thumbnailUrl: String = ""
 ) : Parcelable


