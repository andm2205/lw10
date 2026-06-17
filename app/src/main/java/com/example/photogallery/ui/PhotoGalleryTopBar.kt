package com.example.photogallery.ui

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoGalleryTopBar(
    onSearch: (String) -> Unit,
    onShowFavorites: () -> Unit,
    onClearFavorites: () -> Unit
) {
    var showSearchField by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            if (showSearchField) {
                TextField(
                    value = query,
                    onValueChange = { query = it },
                    placeholder = { Text("Поиск...") },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {
                        onSearch(query)
                        showSearchField = false
                    })
                )
            } else {
                Text("PhotoGallery")
            }
        },
        actions = {
            IconButton(onClick = { showSearchField = !showSearchField }) {
                Icon(Icons.Default.Search, contentDescription = "Поиск")
            }
            IconButton(onClick = { showMenu = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = "Меню")
            }
            DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                DropdownMenuItem(
                    text = { Text("Избранное") },
                    onClick = { showMenu = false; onShowFavorites() }
                )
                DropdownMenuItem(
                    text = { Text("Очистить избранное") },
                    onClick = { showMenu = false; onClearFavorites() }
                )
            }
        }
    )
}