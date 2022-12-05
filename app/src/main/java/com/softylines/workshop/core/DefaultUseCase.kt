package com.softylines.workshop.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import retrofit2.HttpException

class DefaultUseCase<T>(private inline val onRequest: suspend () -> Flow<T>) : UseCase<T> {
    override suspend fun execute(): Flow<Resource<T>> = flow {
        try {
            emit(Resource.Loading<T>())
            onRequest().collect { T ->
                emit(Resource.Success<T>(T))
            }
        } catch (throwable: Throwable) {
            if (throwable is HttpException) {
                val error = JSONObject(throwable.response()?.errorBody()!!.string())
                emit(Resource.Error<T>(message = error["message"] as String))
            } else {
                throwable.printStackTrace()
                emit(Resource.Error<T>(message = "Couldn't reach server . Check your internet connection"))
            }
        }
    }
}