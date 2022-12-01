package com.softylines.workshop.feature_pictures.domain.use_case

import com.softylines.workshop.core.Resource
import com.softylines.workshop.feature_pictures.domain.model.Picture
import com.softylines.workshop.feature_pictures.domain.repository.PictureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPicturesUseCase @Inject constructor(
    private val repository: PictureRepository,
) {
    suspend operator fun invoke(
        fetchFromRemote: Boolean,
    ): Flow<Resource<List<Picture>>> = flow {
        try {
            if (fetchFromRemote) {
                emit(Resource.Loading<List<Picture>>())
            }
            repository.getPictures(fetchFromRemote).collect {
                emit(Resource.Success<List<Picture>>(it))
            }
        } catch (e: HttpException) {
            emit(Resource.Error<List<Picture>>(e.localizedMessage ?: "An unexpected error occur"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Picture>>("Couldn't reach server . Check your internet connection"))
        }
    }
}