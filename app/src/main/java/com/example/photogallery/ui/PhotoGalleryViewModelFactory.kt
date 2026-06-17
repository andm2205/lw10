package com.example.photogallery.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.photogallery.data.PhotoRepository

class PhotoGalleryViewModelFactory(
    private val repository: PhotoRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PhotoGalleryViewModel(repository) as T
    }
}