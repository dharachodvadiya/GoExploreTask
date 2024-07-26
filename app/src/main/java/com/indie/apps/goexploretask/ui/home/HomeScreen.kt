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
import com.indie.apps.goexploretask.R
import com.indie.apps.goexploretask.ui.theme.GoExploreTaskTheme

@Composable
fun HomeScreen(
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
                modifier = Modifier
                    .padding(vertical = dimensionResource(id = R.dimen.padding))
            )
            visualsSection()
            ItemGrid(
                lable = R.string.places,
                modifier = Modifier
                    .padding(vertical = dimensionResource(id = R.dimen.padding))
            )
        }

        bottomSection(
            onNextClick = {},
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