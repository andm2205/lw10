package com.example.photogallery.data

import com.example.photogallery.network.PhotoResponse
import com.example.photogallery.network.PixabayApiService
import kotlinx.coroutines.flow.Flow

class PhotoRepository(private val dao: FavoritePhotoDao) {

    suspend fun fetchInterestingPhotos(): List<PhotoResponse> =
        PixabayApiService.api.fetchInterestingPhotos().hits

    suspend fun searchPhotos(query: String): List<PhotoResponse> =
        PixabayApiService.api.searchPhotos(query).hits

    fun getFavorites(): Flow<List<FavoritePhoto>> = dao.getAll()

    suspend fun addFavorite(photo: PhotoResponse) {
        dao.insert(FavoritePhoto(photo.id, photo.tags, photo.urlSmall))
    }

    suspend fun clearFavorites() = dao.clearAll()
}