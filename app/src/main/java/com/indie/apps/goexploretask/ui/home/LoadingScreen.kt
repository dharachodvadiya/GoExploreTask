package com.indie.apps.goexploretask.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.indie.apps.goexploretask.R
import com.indie.apps.goexploretask.ui.theme.GoExploreTaskTheme

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    )
    {
        Text(
            text = stringResource(R.string.loading),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingScreenPreview() {
    GoExploreTaskTheme {
        LoadingScreen(
        )
    }
}