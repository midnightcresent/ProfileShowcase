package com.adityrajiv.profileshowcase.presentation.profile

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adityrajiv.profileshowcase.presentation.profile.components.DashboardRow
import com.adityrajiv.profileshowcase.presentation.profile.components.PaletteRow
import com.adityrajiv.profileshowcase.presentation.profile.components.ProfileContent
import com.adityrajiv.profileshowcase.presentation.profile.components.ProfileHeader
import com.adityrajiv.profileshowcase.presentation.profile.components.ProfileStats
import com.adityrajiv.profileshowcase.presentation.profile.components.ProfileTopBar
import com.adityrajiv.profileshowcase.presentation.profile.util.ProfileStatItem

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    username: String,
    profilePhotoUrl: String,
    paletteImageUrl: String,
    uploadsState: ProfileViewModel.UploadsState,
    primaryStatItems: List<ProfileStatItem>,
    secondaryStatItems: List<ProfileStatItem>,
    onUserButtonClick: () -> Unit,
    onCreateButtonClick: () -> Unit,
    onDrawerButtonClick: () -> Unit,
    onUploadButtonClick: () -> Unit,
    onEditButtonClick: () -> Unit,
) {
    // Scrolling is for supporting screen usage in landscape mode.
    /* Use a Lazy Column instead of a Column with vertical scroll, to avoid scrolling conflict
    with VerticalGrid in ProfileContent. */
    LazyColumn(modifier = modifier) {
        item {
            ProfileTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                onUserButtonClick = onUserButtonClick,
                onCreateButtonClick = onCreateButtonClick,
                onDrawerButtonClick = onDrawerButtonClick
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            ProfileHeader(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp),
                username = username,
                profilePhotoUrl = profilePhotoUrl,
                onUploadButtonClick = onUploadButtonClick,
                onEditButtonClick = onEditButtonClick
            )
            Spacer(modifier = Modifier.height(14.dp))
        }
        item {
            DashboardRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            ProfileStats(
                modifier = Modifier.fillMaxWidth(),
                primaryStatItems = primaryStatItems,
                secondaryStatItems = secondaryStatItems
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            PaletteRow(
                modifier = Modifier.fillMaxWidth(),
                paletteImageUrl = paletteImageUrl
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            ProfileContent(uploadsState = uploadsState)
        }
    }
}