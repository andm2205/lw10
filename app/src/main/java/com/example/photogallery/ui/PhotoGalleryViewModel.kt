package com.example.photogallery.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photogallery.data.PhotoRepository
import com.example.photogallery.network.PhotoResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PhotoGalleryViewModel(
    private val repository: PhotoRepository
) : ViewModel() {

    private val _photos = MutableStateFlow<List<PhotoResponse>>(emptyList())
    val photos: StateFlow<List<PhotoResponse>> = _photos.asStateFlow()

    val favorites = repository.getFavorites()

    init { loadInterestingPhotos() }

    fun loadInterestingPhotos() {
        viewModelScope.launch {
            _photos.value = repository.fetchInterestingPhotos()
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            _photos.value = repository.searchPhotos(query)
        }
    }

    fun onPhotoClick(photo: PhotoResponse) {
        viewModelScope.launch { repository.addFavorite(photo) }
    }

    fun clearFavorites() {
        viewModelScope.launch { repository.clearFavorites() }
    }
}