package com.cont96roller.library.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Review(
    var title: String,
    var book: String,
    val thumbnail: String = ""
) : Parcelable {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
