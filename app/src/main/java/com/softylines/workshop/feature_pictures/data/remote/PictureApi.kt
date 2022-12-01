package com.softylines.workshop.feature_pictures.data.remote

import com.softylines.workshop.core.Constants.APP_ID
import com.softylines.workshop.feature_pictures.data.remote.response.PictureResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureApi {
    @GET("photos/?client_id=${APP_ID}")
    suspend fun getPictures(
        @Query("page") page: Int,
        @Query("per_page") pageLimit: Int,
        @Query("order_by") order: String,
    ): List<PictureResponse>
}