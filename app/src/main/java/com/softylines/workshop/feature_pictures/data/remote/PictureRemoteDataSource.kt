package com.softylines.workshop.feature_pictures.data.remote

import com.softylines.workshop.feature_pictures.data.remote.response.PictureResponse
import javax.inject.Inject

class PictureRemoteDataSource @Inject constructor(
    private val api: PictureApi,
) {
    suspend fun getPictures(): List<PictureResponse> {
        return api.getPictures(1, 30, "sort")
    }
}