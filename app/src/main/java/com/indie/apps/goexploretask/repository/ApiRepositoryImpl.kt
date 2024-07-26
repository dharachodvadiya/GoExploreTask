package com.indie.apps.goexploretask.repository

import com.indie.apps.goexploretask.data.ApiService

class ApiRepositoryImpl(private val aoiService: ApiService) : ApiRepository {
    override suspend fun getData() = aoiService.getData()
}