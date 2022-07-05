package com.gmail.pentominto.us.mtgcardrulings.di

import android.content.Context
import androidx.room.Room
import com.gmail.pentominto.us.mtgcardrulings.db.FavoritesDao
import com.gmail.pentominto.us.mtgcardrulings.db.FavoritesDatabase
import com.gmail.pentominto.us.mtgcardrulings.favorites.usecasae.GetSearchResultsUseCase
import com.gmail.pentominto.us.mtgcardrulings.favorites.usecasae.IGetSearchResultsUseCase
import com.gmail.pentominto.us.mtgcardrulings.repository.DefaultRepository
import com.gmail.pentominto.us.mtgcardrulings.repository.IDefaultRepository
import com.gmail.pentominto.us.mtgcardrulings.utility.Constants.BASE_URL
import com.gmail.pentominto.us.mtgcardrulings.utility.Constants.CARD_DATABASE_NAME
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app : Context
    ) = Room.databaseBuilder(
        app,
        FavoritesDatabase::class.java,
        CARD_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun providesDao(database : FavoritesDatabase) : FavoritesDao {
        return database.getFavoritesDao()
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface AppModuleInt {

        @Singleton
        @Binds
        fun providesDefaultRepository(repository : DefaultRepository) : IDefaultRepository

        @Singleton
        @Binds
        fun provideSearchResultsUseCase(uc : GetSearchResultsUseCase) : IGetSearchResultsUseCase
    }
}