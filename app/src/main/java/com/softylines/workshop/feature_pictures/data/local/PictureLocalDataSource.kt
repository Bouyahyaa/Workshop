package com.softylines.workshop.feature_pictures.data.local

import javax.inject.Inject

class PictureLocalDataSource @Inject constructor(
    private val dao: PictureDao,
) {
    fun getPictures() = dao.getPictures()

    suspend fun insertPicture(pictureEntity: PictureEntity) = dao.insertPicture(pictureEntity)

    suspend fun deletePicture(pictureEntity: PictureEntity) = dao.deletePicture(pictureEntity)

    suspend fun clearPictures() = dao.clearPictures()
}