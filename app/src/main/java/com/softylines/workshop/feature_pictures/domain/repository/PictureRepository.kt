package com.softylines.workshop.feature_pictures.domain.repository

import com.softylines.workshop.feature_pictures.domain.model.Picture
import kotlinx.coroutines.flow.Flow

interface PictureRepository {
    suspend fun getPictures(fetchFromRemote: Boolean): Flow<List<Picture>>
    suspend fun deletePicture(picture: Picture)
    suspend fun addPicture(picture: Picture)
}