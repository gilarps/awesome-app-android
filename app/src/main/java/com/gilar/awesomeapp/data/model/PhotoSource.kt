package com.gilar.awesomeapp.data.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class PhotoSource(

    val small: String? = null,

    val original: String? = null,

    val large: String? = null,

    val tiny: String? = null,

    val medium: String? = null,

    val large2x: String? = null,

    val portrait: String? = null,

    val landscape: String? = null
) : Parcelable