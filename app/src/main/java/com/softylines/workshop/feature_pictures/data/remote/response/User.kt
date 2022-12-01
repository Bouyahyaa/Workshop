package com.softylines.workshop.feature_pictures.data.remote.response

import com.softylines.workshop.feature_pictures.data.remote.response.ProfileImage

data class User(
    val id: String,
    val name: String,
    val profile_image: ProfileImage,
    val username: String
)