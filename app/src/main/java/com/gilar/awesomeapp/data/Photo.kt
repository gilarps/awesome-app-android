package com.gilar.awesomeapp.data

import com.squareup.moshi.Json

data class Photo(

    val src: PhotoSource? = null,

    val width: Int? = null,

    @Json(name = "avg_color")
    val avgColor: String? = null,

    val alt: String? = null,

    val photographer: String? = null,

    @Json(name = "photographer_url")
    val photographerUrl: String? = null,

    val id: Int? = null,

    val url: String? = null,

    @Json(name = "photographer_id")
    val photographerId: Int? = null,

    val liked: Boolean? = null,

    val height: Int? = null
)