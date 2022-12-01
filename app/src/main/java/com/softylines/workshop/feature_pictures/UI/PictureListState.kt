package com.softylines.workshop.feature_pictures.UI

import com.softylines.workshop.feature_pictures.domain.model.Picture

data class PictureListState(
    val isLoading: Boolean = false,
    val pictures: List<Picture> = emptyList(),
    val error: String = "",
)