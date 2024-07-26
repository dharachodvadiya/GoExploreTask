package com.indie.apps.goexploretask.repository

import com.indie.apps.goexploretask.data.model.ApiResponse
import retrofit2.Response

interface ApiRepository {
    suspend fun getData() : Response<ApiResponse>
}