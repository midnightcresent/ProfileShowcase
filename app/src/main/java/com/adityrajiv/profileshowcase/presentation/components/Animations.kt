package com.adityrajiv.profileshowcase.presentation.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.adityrajiv.profileshowcase.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun ImageLoadingAnimation() {
    val animation = rememberInfiniteTransition(label = "")
    val progress by animation.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Restart,
        ), label = ""
    )

    Box(
        modifier = Modifier
            .size(60.dp)
            .scale(progress)
            .alpha(1f - progress)
            .border(
                5.dp,
                color = Color.LightGray,
                shape = CircleShape
            )
    )
}

@Composable
fun ErrorAnimation(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        LottieLoader(
            modifier = Modifier.size(50.dp),
            resId = R.raw.error_animation
        )
    }
}

@Composable
fun EmptyAnimation(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        LottieLoader(
            modifier = Modifier.size(200.dp),
            resId = R.raw.empty_list
        )
    }
}

@Composable
fun LoadingAnimation(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        LottieLoader(
            modifier = Modifier.size(200.dp),
            resId = R.raw.loading,
            loop = true
        )
    }
}

@Composable
fun LottieLoader(
    modifier: Modifier = Modifier,
    resId: Int,
    loop: Boolean = false
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(resId))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        reverseOnRepeat = loop
    )
    LottieAnimation(
        modifier = modifier,
        composition = composition,
        progress = { progress },
    )
}