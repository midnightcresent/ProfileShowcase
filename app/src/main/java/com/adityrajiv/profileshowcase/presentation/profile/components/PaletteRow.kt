package com.adityrajiv.profileshowcase.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.adityrajiv.profileshowcase.R
import com.adityrajiv.profileshowcase.presentation.components.ImageLoadingAnimation
import com.adityrajiv.profileshowcase.presentation.theme.BarlowCondensed

@Composable
fun PaletteRow(
    modifier: Modifier = Modifier,
    paletteImageUrl: String
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier.height(42.dp),
            model = paletteImageUrl,
            contentDescription = stringResource(R.string.paletteText),
            contentScale = ContentScale.Crop,
            loading = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    ImageLoadingAnimation()
                }
            }
        )
        Text(
            modifier = Modifier.padding(start = 12.dp),
            text = stringResource(R.string.paletteText).lowercase(),
            fontFamily = BarlowCondensed,
            color = Color.White,
        )
    }
}