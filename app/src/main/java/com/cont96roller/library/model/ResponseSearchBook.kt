package com.cont96roller.library.model

data class ResponseSearchBook(val meta: Meta, val documents: MutableList<Documents>)
data class Meta(val is_end: Boolean, val pageable_count: Int, val total_count: Int)
data class Documents(
    val authors: Array<String>,
    val contents: String = "",
    val dataTime: String = "",
    val isbn: String = "",
    val publisher: String = "",
    val thumbnail: String = "",
    val title: String = "",
    val url: String = ""
)