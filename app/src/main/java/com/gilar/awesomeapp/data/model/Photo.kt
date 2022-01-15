package com.gilar.awesomeapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Photo(

    val src: PhotoSource? = null,

//    val width: Int? = null,

    @Json(name = "avg_color")
    val avgColor: String? = null,

//    val alt: String? = null,

    val photographer: String? = null,

    @Json(name = "photographer_url")
    val photographerUrl: String? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "url")
    val url: String? = null,

    @Json(name = "photographer_id")
    val photographerId: Int? = null,

//    val liked: Boolean? = null,

//    val height: Int? = null
)