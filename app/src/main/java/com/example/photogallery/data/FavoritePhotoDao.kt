package com.example.photogallery.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritePhotoDao {
    @Query("SELECT * FROM favorite_photos")
    fun getAll(): Flow<List<FavoritePhoto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: FavoritePhoto)

    @Query("DELETE FROM favorite_photos")
    suspend fun clearAll()
}