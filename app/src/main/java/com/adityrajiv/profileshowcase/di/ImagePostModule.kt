package com.adityrajiv.profileshowcase.di

import com.adityrajiv.profileshowcase.data.repository.ImagePostApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ImagePostModule {

    @Provides
    @Singleton
    fun provideRetrofitInstanceForImagePosts(): ImagePostApi {
        val okHttpClient = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImagePostApi::class.java)
    }
}