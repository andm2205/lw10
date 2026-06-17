package com.example.photogallery.network

import com.squareup.moshi.Json

data class PixabayResponse(
    val total: Int,
    val totalHits: Int,
    val hits: List<PhotoResponse>
)

data class PhotoResponse(
    val id: Int,
    val tags: String,
    @Json(name = "webformatURL") val urlSmall: String?
)