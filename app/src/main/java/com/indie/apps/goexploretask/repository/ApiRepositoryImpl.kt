package com.indie.apps.goexploretask.repository

import com.indie.apps.goexploretask.data.ApiService
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val aoiService: ApiService) : ApiRepository {
    override suspend fun getData() = aoiService.getData()
}