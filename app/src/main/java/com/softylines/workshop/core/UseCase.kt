package com.softylines.workshop.core

import kotlinx.coroutines.flow.Flow

interface UseCase<T> {
    suspend fun execute(): Flow<Resource<T>>
}