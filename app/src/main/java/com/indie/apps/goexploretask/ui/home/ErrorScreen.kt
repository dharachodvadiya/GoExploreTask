package com.indie.apps.goexploretask.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.indie.apps.goexploretask.R
import com.indie.apps.goexploretask.ui.theme.GoExploreTaskTheme

@Composable
fun ErrorScreen(
    message: String = "",
    onRetry : ()-> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding))
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center
            )

            Button(
                onClick = onRetry,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .size(width = 200.dp, height = 50.dp),
            ){
                Text(
                    text = stringResource(R.string.retry),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorScreenPreview() {
    GoExploreTaskTheme {
        ErrorScreen(
            onRetry = {}
        )
    }
}