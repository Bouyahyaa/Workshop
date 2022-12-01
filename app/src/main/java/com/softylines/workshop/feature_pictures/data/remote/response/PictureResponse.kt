package com.softylines.workshop.feature_pictures.data.remote.response

data class PictureResponse(
    val color: String?,
    val created_at: String?,
    val description: String?,
    val height: Int?,
    val id: String?,
    val likes: Int?,
    val updated_at: String?,
    val urls: Urls?,
    val user: User?,
    val width: Int?
)