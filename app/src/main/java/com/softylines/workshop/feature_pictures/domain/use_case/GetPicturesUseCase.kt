package com.softylines.workshop.feature_pictures.domain.use_case

import com.softylines.workshop.core.DefaultUseCase
import com.softylines.workshop.core.Resource
import com.softylines.workshop.feature_pictures.domain.model.Picture
import com.softylines.workshop.feature_pictures.domain.repository.PictureRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPicturesUseCase @Inject constructor(
    private val repository: PictureRepository,
) {
    suspend operator fun invoke(
        fetchFromRemote: Boolean,
    ): Flow<Resource<List<Picture>>> {
        return DefaultUseCase(
            onRequest = {
                repository.getPictures(fetchFromRemote)
            }
        ).execute()
    }
}