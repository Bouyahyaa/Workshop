package com.softylines.workshop.di

import android.app.Application
import androidx.room.Room
import com.softylines.workshop.core.Constants.BASE_URL
import com.softylines.workshop.feature_pictures.data.local.PictureLocalDataSource
import com.softylines.workshop.feature_pictures.data.local.WorkshopDatabase
import com.softylines.workshop.feature_pictures.data.remote.PictureApi
import com.softylines.workshop.feature_pictures.data.remote.PictureRemoteDataSource
import com.softylines.workshop.feature_pictures.data.repository.PictureRepositoryImpl
import com.softylines.workshop.feature_pictures.domain.repository.PictureRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePictureApi(): PictureApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(PictureApi::class.java)
    }


    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): WorkshopDatabase {
        return Room.databaseBuilder(
            app, WorkshopDatabase::class.java, WorkshopDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
    }


    @Singleton
    @Provides
    fun provideUnsplashDao(db: WorkshopDatabase) = db.pictureDao

    @Provides
    @Singleton
    fun providePictureRepository(
        pictureLocalSource: PictureLocalDataSource,
        pictureRemoteSource: PictureRemoteDataSource,
    ): PictureRepository {
        return PictureRepositoryImpl(pictureLocalSource, pictureRemoteSource)
    }
}