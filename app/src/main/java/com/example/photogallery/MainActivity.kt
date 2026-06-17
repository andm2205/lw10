package com.example.photogallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.photogallery.data.PhotoGalleryDatabase
import com.example.photogallery.data.PhotoRepository
import com.example.photogallery.ui.FavoritesScreen
import com.example.photogallery.ui.PhotoGalleryTopBar
import com.example.photogallery.ui.PhotoGalleryViewModel
import com.example.photogallery.ui.PhotoGalleryViewModelFactory
import com.example.photogallery.ui.theme.PhotoGalleryTheme
import androidx.room.Room

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = Room.databaseBuilder(
            applicationContext,
            PhotoGalleryDatabase::class.java,
            "photo_gallery.db"
        ).build()
        val repository = PhotoRepository(db.favoritePhotoDao())

        setContent {
            PhotoGalleryTheme {
                val viewModel: PhotoGalleryViewModel = viewModel(
                    factory = PhotoGalleryViewModelFactory(repository)
                )
                var showFavorites by remember { mutableStateOf(false) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        PhotoGalleryTopBar(
                            onSearch = { query -> viewModel.search(query) },
                            onShowFavorites = { showFavorites = true },
                            onClearFavorites = { viewModel.clearFavorites() }
                        )
                    }
                ) { innerPadding ->
                    if (showFavorites) {
                        val favorites by viewModel.favorites.collectAsState(initial = emptyList())
                        FavoritesScreen(
                            favorites = favorites,
                            modifier = Modifier.padding(innerPadding)
                        )
                    } else {
                        PhotoGalleryScreen(
                            modifier = Modifier.padding(innerPadding),
                            viewModel = viewModel
                        )
                    }
                }

                BackHandler(enabled = showFavorites) {
                    showFavorites = false
                }
            }
        }
    }
}

@Composable
fun PhotoGalleryScreen(
    modifier: Modifier = Modifier,
    viewModel: PhotoGalleryViewModel
) {
    val photos by viewModel.photos.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        items(photos) { photo ->
            AsyncImage(
                model = photo.urlSmall,
                contentDescription = photo.tags,
                modifier = Modifier
                    .padding(2.dp)
                    .aspectRatio(1f)
                    .clickable { viewModel.onPhotoClick(photo) },
                contentScale = ContentScale.Crop
            )
        }
    }
}
