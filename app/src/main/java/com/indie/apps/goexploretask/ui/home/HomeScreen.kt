package com.indie.apps.goexploretask.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.indie.apps.goexploretask.R
import com.indie.apps.goexploretask.data.model.ApiResponse
import com.indie.apps.goexploretask.ui.theme.GoExploreTaskTheme
import com.indie.apps.goexploretask.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    when(viewModel.uiState)
    {
        is Resource.Loading -> {
            LoadingScreen()
        }
        is Resource.Success -> {
            viewModel.uiState.data?.let {
                HomeScreenData(
                    apiResponse = it,
                    onNextClick = onNextClick,
                    modifier = modifier
                )
            }
        }
        is Resource.Error -> {
            viewModel.uiState.message?.let {
                ErrorScreen(
                    message = it,
                    onRetry = {
                        viewModel.getData()
                    }
                )
            }
        }
    }
}

@Composable
fun HomeScreenData(
    apiResponse: ApiResponse,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        titleSection(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding))
                .padding(horizontal = 5.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            ItemGrid(
                lable = R.string.sound,
                dataList = apiResponse.Sound,
                modifier = Modifier
                    .padding(vertical = dimensionResource(id = R.dimen.padding))
            )
            visualsSection()
            ItemGrid(
                lable = R.string.places,
                dataList = apiResponse.Places,
                modifier = Modifier
                    .padding(vertical = dimensionResource(id = R.dimen.padding))
            )
        }

        bottomSection(
            onNextClick = onNextClick,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding))
        )
    }
}

@Preview(showBackground = true)

@Composable
private fun HomeScreenPreview() {
    GoExploreTaskTheme {
        HomeScreen(
            onNextClick = {}
        )
    }
}