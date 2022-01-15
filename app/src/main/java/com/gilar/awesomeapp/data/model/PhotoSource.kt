package com.gilar.awesomeapp.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoSource(

    val small: String? = null,

    val original: String? = null,

    val large: String? = null,

    val tiny: String? = null,

    val medium: String? = null,

    val large2x: String? = null,

    val portrait: String? = null,

    val landscape: String? = null
)