package com.indie.apps.goexploretask.ui.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indie.apps.goexploretask.data.model.ApiResponse
import com.indie.apps.goexploretask.data.model.LableWithEmoji
import com.indie.apps.goexploretask.repository.ApiRepository
import com.indie.apps.goexploretask.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {

    var uiState: Resource<ApiResponse> by mutableStateOf(Resource.Loading())
        private set

    var currentSound : LableWithEmoji by mutableStateOf(LableWithEmoji("",""))
        private set
    var currentPlace : LableWithEmoji by mutableStateOf(LableWithEmoji("",""))
        private set

    init {
        getData()
    }
    fun getData()
    {
        viewModelScope.launch{
            safeLoadData()
        }

    }

    private suspend fun safeLoadData() {
        uiState = Resource.Loading()
        try {
            val response = repository.getData()
            if(response.isSuccessful) {
                response.body()?.let { resultResponse ->
                    if(resultResponse.Places.isNotEmpty())
                        currentPlace = resultResponse.Places[0]
                    if(resultResponse.Sound.isNotEmpty())
                        currentSound = resultResponse.Sound[0]
                    uiState =   Resource.Success(resultResponse)
                }
            }else{
                uiState = Resource.Error(response.message())
            }
        } catch(t: Throwable) {
            when(t) {
                is IOException -> uiState = Resource.Error(" Network Failure")
                else -> uiState = Resource.Error(" Conversion Error")
            }
        }
    }

    fun setSelectedSound(sound : LableWithEmoji)
    {
        currentSound = sound
    }

    fun setSelectedPlace(place : LableWithEmoji)
    {
        currentPlace = place
    }
}