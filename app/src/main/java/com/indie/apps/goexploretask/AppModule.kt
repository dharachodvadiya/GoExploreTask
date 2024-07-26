package com.indie.apps.goexploretask

import com.indie.apps.goexploretask.data.ApiService
import com.indie.apps.goexploretask.repository.ApiRepository
import com.indie.apps.goexploretask.repository.ApiRepositoryImpl
import com.indie.apps.goexploretask.util.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
    private val repo: ApiRepository by lazy {
        ApiRepositoryImpl(api)
    }
}