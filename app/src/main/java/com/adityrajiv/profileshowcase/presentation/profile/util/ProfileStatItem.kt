package com.adityrajiv.profileshowcase.presentation.profile.util

import androidx.compose.ui.graphics.Color

data class ProfileStatItem(
    val metric: Int,
    val label: String,
    val iconId: Int?,
    val iconTint: Color? = null
)