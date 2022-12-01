package com.softylines.workshop.feature_pictures.UI

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.hilt.navigation.compose.hiltViewModel
import java.util.UUID

@Composable
fun PictureListScreen(
    viewModel: PictureListViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    Surface(modifier = Modifier.fillMaxSize()) {
        if (state.pictures.isEmpty() && !state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "No Pictures")
            }
        }

        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        LazyColumn {
            items(state.pictures) { picture ->
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterStart),
                        text = picture.name ?: "No name"
                    )

                    IconButton(
                        modifier = Modifier.align(Alignment.Center),
                        onClick = {
                            viewModel.onEvent(PictureListEvent.OnAddPicture(
                                picture.copy(id = UUID.randomUUID().toString())
                            ))
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Picture"
                        )
                    }

                    IconButton(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        onClick = {
                            viewModel.onEvent(PictureListEvent.OnDeletePicture(picture))
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Picture"
                        )
                    }
                }
            }
        }
    }
}