package com.softylines.workshop.feature_pictures.data.repository

import com.softylines.workshop.feature_pictures.data.local.PictureLocalDataSource
import com.softylines.workshop.feature_pictures.data.mapper.toPicture
import com.softylines.workshop.feature_pictures.data.mapper.toPictureEntity
import com.softylines.workshop.feature_pictures.data.remote.PictureRemoteDataSource
import com.softylines.workshop.feature_pictures.data.remote.response.PictureResponse
import com.softylines.workshop.feature_pictures.domain.model.Picture
import com.softylines.workshop.feature_pictures.domain.repository.PictureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PictureRepositoryImpl @Inject constructor(
    private val pictureLocalDataSource: PictureLocalDataSource,
    private val pictureRemoteDataSource: PictureRemoteDataSource,
) : PictureRepository {
    override suspend fun getPictures(
        fetchFromRemote: Boolean,
    ): Flow<List<Picture>> {
        var remotePictures: List<PictureResponse> = emptyList()

        val shouldJustLoadFromCache = !fetchFromRemote

        if (!shouldJustLoadFromCache) {
            remotePictures = pictureRemoteDataSource.getPictures()
            remotePictures.let { pictures ->
                pictureLocalDataSource.clearPictures()
                pictures.map {
                    pictureLocalDataSource.insertPicture(it.toPictureEntity())
                }
            }
        }
        return pictureLocalDataSource.getPictures()
            .map { list -> list.map { pictureEntity -> pictureEntity.toPicture() } }
    }

    override suspend fun deletePicture(picture: Picture) {
        pictureLocalDataSource.deletePicture(picture.toPictureEntity())
    }

    override suspend fun addPicture(picture: Picture) {
        pictureLocalDataSource.insertPicture(picture.toPictureEntity())
    }
}