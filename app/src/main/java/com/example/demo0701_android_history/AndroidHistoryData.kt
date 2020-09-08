package com.example.demo0701_android_history

import com.squareup.moshi.Json

data class AndroidHistoryData (
    val version_name: String, @Json(name = "img_src") val imgSrcUrl: String,
    val version_id: String,
    val release_year: String
)