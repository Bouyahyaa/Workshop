package com.softylines.workshop.feature_pictures.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PictureDao {
    @Query("SELECT * FROM pictures")
    fun getPictures(): Flow<List<PictureEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPicture(picture: PictureEntity)

    @Delete
    suspend fun deletePicture(picture: PictureEntity)

    @Query("DELETE FROM pictures")
    suspend fun clearPictures()
}