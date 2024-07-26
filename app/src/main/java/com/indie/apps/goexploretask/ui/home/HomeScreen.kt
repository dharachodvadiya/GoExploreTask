package com.indie.apps.goexploretask.ui.home

import androidx.compose.foundation.layout.Column
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
import com.indie.apps.goexploretask.data.model.LableWithEmoji
import com.indie.apps.goexploretask.ui.theme.GoExploreTaskTheme
import com.indie.apps.goexploretask.util.Resource

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNextClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (viewModel.uiState) {
        is Resource.Loading -> {
            LoadingScreen()
        }

        is Resource.Success -> {
            viewModel.uiState.data?.let {
                HomeScreenData(
                    apiResponse = it,
                    onNextClick = {
                        onNextClick(
                            viewModel.currentSound.label,
                            viewModel.currentPlace.label
                        )
                    },
                    currentSound = viewModel.currentSound,
                    currentPlace = viewModel.currentPlace,
                    onPlacesChange = viewModel::setSelectedPlace,
                    onSoundChange = viewModel::setSelectedSound,
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
    currentSound: LableWithEmoji,
    currentPlace: LableWithEmoji,
    onNextClick: () -> Unit,
    onSoundChange: (LableWithEmoji) -> Unit,
    onPlacesChange: (LableWithEmoji) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        TitleSection(
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
                onItemClick = {
                    onSoundChange(it)
                },
                currentData = currentSound,
                lable = R.string.sound,
                dataList = apiResponse.Sound,
                modifier = Modifier
                    .padding(vertical = dimensionResource(id = R.dimen.padding))
            )
            VisualsSection(
                dataList = apiResponse.Visuals
            )
            ItemGrid(
                onItemClick = onPlacesChange,
                currentData = currentPlace,
                lable = R.string.places,
                dataList = apiResponse.Places,
                modifier = Modifier
                    .padding(vertical = dimensionResource(id = R.dimen.padding))
            )
        }

        BottomSection(
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
            onNextClick = { a, b -> }
        )
    }
}