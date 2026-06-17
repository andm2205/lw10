package com.example.photogallery.network

import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "ВАШ_PIXABAY_API_KEY"

interface PixabayApi {

    @GET("api/?key=$API_KEY&order=popular")
    suspend fun fetchInterestingPhotos(): PixabayResponse

    @GET("api/?key=$API_KEY")
    suspend fun searchPhotos(@Query("q") query: String): PixabayResponse
}