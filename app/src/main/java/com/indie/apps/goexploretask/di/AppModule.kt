package com.indie.apps.goexploretask.di

import com.indie.apps.goexploretask.data.ApiService
import com.indie.apps.goexploretask.repository.ApiRepository
import com.indie.apps.goexploretask.repository.ApiRepositoryImpl
import com.indie.apps.goexploretask.util.Constant
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
    fun providePaprikaApi(): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideRepository(api: ApiService): ApiRepository {
        return ApiRepositoryImpl(api)
    }
}