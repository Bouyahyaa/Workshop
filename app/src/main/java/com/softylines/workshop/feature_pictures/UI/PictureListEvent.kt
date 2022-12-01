package com.softylines.workshop.feature_pictures.UI

import com.softylines.workshop.feature_pictures.domain.model.Picture

sealed class PictureListEvent {
    data class OnDeletePicture(val picture: Picture) : PictureListEvent()
    data class OnAddPicture(val picture: Picture) : PictureListEvent()
}