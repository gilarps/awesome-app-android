package com.gilar.awesomeapp.data

import com.gilar.awesomeapp.data.model.Photo
import com.gilar.awesomeapp.data.model.PhotosCuratedResponse
import com.gilar.awesomeapp.util.FileReaderUtil
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

object PhotosData {
    private val moshi = Moshi.Builder().build()
    private val remoteMoviesAdapter: JsonAdapter<PhotosCuratedResponse> =
        moshi.adapter(PhotosCuratedResponse::class.java)

    fun provideRemotePhotosFromAssets(): List<Photo> {
        return remoteMoviesAdapter.fromJson(
            FileReaderUtil.kotlinReadFileWithNewLineFromResources(
                fileName = "photos.json"
            )
        )?.photos ?: listOf()
    }
}