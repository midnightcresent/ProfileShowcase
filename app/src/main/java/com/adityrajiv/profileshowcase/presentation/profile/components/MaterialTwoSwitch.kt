package com.adityrajiv.profileshowcase.presentation.profile.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils

@Composable
fun MaterialTwoSwitch(
    checked: Boolean,
    onCheckedChange: () -> Unit,
    modifier: Modifier = Modifier,
    colors: MaterialTwoSwitchColors = MaterialTwoSwitchDefaults.colors()
) {
    val trackWidth = 46.dp
    val trackHeight = 17.dp
    val thumbDiameter = 22.dp

    Box(
        modifier = modifier
            .width(trackWidth)
            .height(trackHeight)
            .background(
                color = if (checked) colors.checkedTrackColor else colors.uncheckedTrackColor,
                shape = MaterialTheme.shapes.small
            )
            .draggable(
                state = remember { DraggableState { /* Handle drag if needed */ } },
                orientation = androidx.compose.foundation.gestures.Orientation.Horizontal
            )
            .clickable(onClick = {
                onCheckedChange()
            }),
        contentAlignment = Alignment.CenterStart
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxHeight()
                .width(thumbDiameter)
        ) {
            val thumbOffset = if (checked) trackWidth.toPx() - thumbDiameter.toPx() else 0f
            // Draw shadow
            drawCircle(
                color = Color.Black.copy(alpha = 0.3f),
                radius = (thumbDiameter.toPx() / 2) + 1f,
                center = Offset(
                    x = thumbOffset + (thumbDiameter.toPx() / 2),
                    y = (size.height / 2) + 1f
                )
            )

            // Draw main thumb circle
            drawCircle(
                color = if (checked) colors.checkedThumbColor else colors.uncheckedThumbColor,
                radius = thumbDiameter.toPx() / 2,
                center = Offset(
                    x = thumbOffset + (thumbDiameter.toPx() / 2),
                    y = size.height / 2
                )
            )

            val darkenedColor = Color(
                ColorUtils.blendARGB(
                    colors.checkedTrackColor.toArgb(),
                    Color.Black.toArgb(),
                    0.2f
                )
            )
            // Draw small inner circle matching track color
            drawCircle(
                color = if (checked) darkenedColor else colors.uncheckedTrackColor,
                radius = thumbDiameter.toPx() / 12,
                center = Offset(
                    x = thumbOffset + (thumbDiameter.toPx() / 2),
                    y = size.height / 2
                )
            )
        }
    }
}

object MaterialTwoSwitchDefaults {
    @Composable
    fun colors(
        checkedThumbColor: Color = Color(0xFFFFFFFF),
        uncheckedThumbColor: Color = Color(0xFFFFFFFF),
        checkedTrackColor: Color = Color(0xFF44A33D),
        uncheckedTrackColor: Color = Color(0xFFBDBDBD)
    ): MaterialTwoSwitchColors {
        return MaterialTwoSwitchColors(
            checkedThumbColor = checkedThumbColor,
            uncheckedThumbColor = uncheckedThumbColor,
            checkedTrackColor = checkedTrackColor,
            uncheckedTrackColor = uncheckedTrackColor
        )
    }
}

data class MaterialTwoSwitchColors(
    val checkedThumbColor: Color,
    val uncheckedThumbColor: Color,
    val checkedTrackColor: Color,
    val uncheckedTrackColor: Color
)

@Preview(showBackground = true)
@Composable
fun SwitchExample() {
    var isChecked by remember { mutableStateOf(true) }

    MaterialTwoSwitch(
        checked = isChecked,
        onCheckedChange = { isChecked = !isChecked }
    )
}