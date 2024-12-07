package com.adityrajiv.profileshowcase.di

import com.adityrajiv.profileshowcase.data.repository.ImagePostApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ImagePostModule {

    @Provides
    @Singleton
    fun provideRetrofitInstanceForImagePosts(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): ImagePostApi {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(ImagePostApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        timeoutInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(timeoutInterceptor)
            .retryOnConnectionFailure(true)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideTimeoutInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            try {
                chain.proceed(request)
            } catch (e: SocketTimeoutException) {
                throw IOException("Connection timeout", e)
            }
        }
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        val gson = GsonBuilder()
            .setLenient()
            .setPrettyPrinting()
            .create()
        return GsonConverterFactory.create(gson)
    }
}