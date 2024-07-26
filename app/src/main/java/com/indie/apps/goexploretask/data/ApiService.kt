package com.indie.apps.goexploretask.data

import com.indie.apps.goexploretask.data.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("interview24/topics.json")
    suspend fun getData(): Response<ApiResponse>
}