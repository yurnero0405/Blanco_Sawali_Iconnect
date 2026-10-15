package com.example.iconnect

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("posts/{id}")
    suspend fun getPost(
        @Path("id") id: Int
    ): Post
}