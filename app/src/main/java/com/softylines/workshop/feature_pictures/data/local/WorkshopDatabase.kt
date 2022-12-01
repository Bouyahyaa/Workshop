package com.softylines.workshop.feature_pictures.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PictureEntity::class],
    version = 1
)

abstract class WorkshopDatabase : RoomDatabase() {
    abstract val pictureDao: PictureDao

    companion object {
        const val DATABASE_NAME = "workshop_db"
    }
}