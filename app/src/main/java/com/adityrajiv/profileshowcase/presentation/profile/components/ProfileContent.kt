package com.adityrajiv.profileshowcase.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.adityrajiv.profileshowcase.R
import com.adityrajiv.profileshowcase.presentation.components.EmptyAnimation
import com.adityrajiv.profileshowcase.presentation.components.ErrorAnimation
import com.adityrajiv.profileshowcase.presentation.components.ImageLoadingAnimation
import com.adityrajiv.profileshowcase.presentation.components.LoadingAnimation
import com.adityrajiv.profileshowcase.presentation.profile.ProfileViewModel
import com.adityrajiv.profileshowcase.presentation.theme.Barlow
import com.adityrajiv.profileshowcase.presentation.theme.BarlowCondensed
import com.adityrajiv.profileshowcase.presentation.theme.color.TabIndicatorYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    uploadsState: ProfileViewModel.UploadsState,
    exhibitions: List<Unit>? = null,
    revenue: Unit? = null
) {

    var tabState by remember {
        mutableIntStateOf(0)
    }

    val titles = listOf(
        TabItem(
            label = stringResource(R.string.uploadButtonAltText),
            icon = ImageVector.vectorResource(R.drawable.upload_icon)
        ),
        TabItem(
            label = stringResource(R.string.exhibitionsTabText),
            icon = ImageVector.vectorResource(R.drawable.image_icon)
        ),
        TabItem(
            label = stringResource(R.string.revenueTabText),
            icon = ImageVector.vectorResource(R.drawable.revenue_icon)
        )
    )

    PrimaryTabRow(
        selectedTabIndex = tabState,
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = LocalContentColor.current,
        indicator = {
            TabRowDefaults.PrimaryIndicator(
                modifier = Modifier.tabIndicatorOffset(
                    tabState,
                    matchContentSize = true
                ), width = Dp.Unspecified,
                color = TabIndicatorYellow
            )
        },
        divider = {} //Remove the default horizontal bar
    ) {
        titles.forEachIndexed { index, item ->
            Tab(
                selected = tabState == index,
                onClick = { tabState = index },
                text = {
                    Text(
                        text = item.label,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = Barlow
                    )
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = LocalContentColor.current.copy(alpha = if (tabState == index) 1f else 0.5f)
                    )
                },
            )
        }
    }
    Spacer(modifier = Modifier.height(12.dp))

    when (tabState) {
        0 -> {
            when (uploadsState) {
                is ProfileViewModel.UploadsState.Loading -> {
                    LoadingAnimation(modifier = Modifier.fillMaxSize())
                }

                is ProfileViewModel.UploadsState.Success -> {
                    TwoColumnImageGrid(uploadsState.urls)
                }

                is ProfileViewModel.UploadsState.Error -> {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ErrorAnimation(modifier = Modifier.fillMaxSize())
                        Text(
                            text = uploadsState.message,
                            fontFamily = BarlowCondensed,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }

        1 -> {
            if (exhibitions.isNullOrEmpty())
                EmptyAnimation(modifier = Modifier.fillMaxSize())
            else
                TODO()
        }

        2 -> {
            if (revenue == null)
                EmptyAnimation(modifier = Modifier.fillMaxSize())
            else
                TODO()
        }
    }
}

@Composable
fun TwoColumnImageGrid(images: List<String>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(270.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // 2 columns
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(images) { imageUrl ->
                ImageCard(imageUrl)
            }
        }
    }
}

@Composable
fun ImageCard(imageUrl: String) {
    SubcomposeAsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(168.dp)
            .width(166.94.dp),
        model = imageUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop, // Crops the image to fit the box
        loading = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.DarkGray),
                contentAlignment = Alignment.Center
            ) {
                ImageLoadingAnimation()
            }
        },
        error = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.DarkGray),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ErrorAnimation()
                Text(
                    text = it.result.throwable.message.toString(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = BarlowCondensed
                )
            }
        }
    )
}

private data class TabItem(
    val label: String,
    val icon: ImageVector
)