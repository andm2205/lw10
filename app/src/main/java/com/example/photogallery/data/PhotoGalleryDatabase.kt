package com.example.photogallery.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoritePhoto::class], version = 1, exportSchema = false)
abstract class PhotoGalleryDatabase : RoomDatabase()  {
    abstract fun favoritePhotoDao(): FavoritePhotoDao
}