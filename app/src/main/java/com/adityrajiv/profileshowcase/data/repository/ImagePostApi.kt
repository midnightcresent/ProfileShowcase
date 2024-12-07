package com.adityrajiv.profileshowcase.data.repository

import com.adityrajiv.profileshowcase.data.model.ImagePosts
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagePostApi {
    @GET("/photos")
    suspend fun getImagePosts(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int,
    ): Response<ImagePosts>
}