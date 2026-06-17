package com.example.photogallery.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_photos")
data class FavoritePhoto(
    @PrimaryKey val id: Int,
    val tags: String,
    val urlSmall: String?
)