package com.indie.apps.goexploretask.ui.home

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.indie.apps.goexploretask.R
import com.indie.apps.goexploretask.data.model.LableWithEmoji
import com.indie.apps.goexploretask.ui.theme.GoExploreTaskTheme

@Composable
fun titleSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = R.string.title1),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding)))

        Text(
            text = stringResource(id = R.string.title2),
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center
        )


    }
}

@Composable
fun ItemGrid(
    @StringRes lable: Int,
    dataList : List<LableWithEmoji>,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
    ) {
        Text(
            text = stringResource(lable),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding))
        )
        LazyHorizontalStaggeredGrid(
            contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding)),
            rows = StaggeredGridCells.Fixed(2),
            modifier = Modifier.height(120.dp),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding)),
            horizontalItemSpacing = dimensionResource(id = R.dimen.padding)
        ) {
            items(dataList){ item ->
                iconWithlableItem(
                    iconText = item.emoji,
                    lable = item.label
                )
            }
        }
    }


}


@Composable
fun visualsSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.visuals),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding))
        )
        LazyRow(
            contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding))
        ) {
            items(5) { item ->

                Surface(
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .size(width = 120.dp, height = 170.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "item",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

            }

        }
    }
}

@Composable
fun bottomSection(
    onNextClick: ()-> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        Button(
            onClick = onNextClick,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .size(width = 200.dp, height = 50.dp),
        ){
            Text(
                text = stringResource(R.string.next),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }
    }

}

@Composable
private fun iconWithlableItem(
    iconText: String,
    lable: String,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = {},
        shape = RoundedCornerShape(50),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .padding(5.dp)
                .padding(horizontal = 15.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = iconText)
            Spacer(modifier = Modifier.width(7.dp))
            Text(
                text = lable,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun titleSectionPreview() {
    GoExploreTaskTheme {
        titleSection()
    }
}

@Preview(showBackground = true)
@Composable
private fun visualsSectionPreview() {
    GoExploreTaskTheme {
        visualsSection()
    }
}