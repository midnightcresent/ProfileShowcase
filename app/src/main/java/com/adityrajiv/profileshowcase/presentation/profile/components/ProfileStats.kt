package com.adityrajiv.profileshowcase.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.adityrajiv.profileshowcase.presentation.profile.util.ProfileStatItem
import com.adityrajiv.profileshowcase.presentation.theme.Barlow
import com.adityrajiv.profileshowcase.presentation.theme.BarlowCondensed
import com.adityrajiv.profileshowcase.presentation.util.convertToKs

@Composable
fun ProfileStats(
    modifier: Modifier = Modifier,
    primaryStatItems: List<ProfileStatItem>,
    secondaryStatItems: List<ProfileStatItem>
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalDivider(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))

        ProfileStatsTop(
            modifier = Modifier.fillMaxWidth(),
            statItems = primaryStatItems
        )

        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(16.dp))
        ProfileStatsBottom(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp),
            statItems = secondaryStatItems
        )
    }
}

@Composable
private fun ProfileStatsTop(
    modifier: Modifier = Modifier,
    statItems: List<ProfileStatItem>
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(statItems) { item ->
            ProfileStat(
                profileStatItem = item
            )
        }
    }
}

@Composable
private fun ProfileStatsBottom(
    modifier: Modifier = Modifier,
    statItems: List<ProfileStatItem>
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(statItems) { item ->
            ProfileStat(
                profileStatItem = item
            )
        }
    }
}

@Composable
private fun ProfileStat(
    modifier: Modifier = Modifier,
    profileStatItem: ProfileStatItem
) {
    if (profileStatItem.iconId != null) {
        // Icon with metric in a Row
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(profileStatItem.iconId),
                tint = profileStatItem.iconTint ?: LocalContentColor.current,
                contentDescription = profileStatItem.label,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = convertToKs(profileStatItem.metric),
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = Barlow,
                fontWeight = FontWeight.Light
            )
        }
    } else {
        // Label below metric in a Column
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = convertToKs(profileStatItem.metric),
                style = MaterialTheme.typography.titleLarge,
                fontFamily = BarlowCondensed
            )
            Text(
                text = profileStatItem.label,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = BarlowCondensed,
                fontWeight = FontWeight.Light
            )
        }
    }
}