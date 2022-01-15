package com.gilar.awesomeapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotosCuratedResponse(

    @Json(name = "next_page")
    val nextPage: String? = null,

    @Json(name = "per_page")
    val perPage: Int? = null,

    @Json(name = "page")
    val page: Int? = null,

    @Json(name = "photos")
    val photos: List<Photo>
)