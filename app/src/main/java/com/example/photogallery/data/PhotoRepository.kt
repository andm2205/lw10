package com.example.photogallery.data

import com.example.photogallery.network.PhotoResponse
import com.example.photogallery.network.PixabayApiService

class PhotoRepository {
    suspend fun fetchInterestingPhotos(): List<PhotoResponse> =
        PixabayApiService.api.fetchInterestingPhotos().hits

    suspend fun searchPhotos(query: String): List<PhotoResponse> =
        PixabayApiService.api.searchPhotos(query).hits
}