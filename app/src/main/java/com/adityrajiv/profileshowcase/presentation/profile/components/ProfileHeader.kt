package com.adityrajiv.profileshowcase.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.adityrajiv.profileshowcase.R
import com.adityrajiv.profileshowcase.presentation.components.ImageLoadingAnimation
import com.adityrajiv.profileshowcase.presentation.profile.util.PROFILE_URL
import com.adityrajiv.profileshowcase.presentation.theme.Barlow
import com.adityrajiv.profileshowcase.presentation.theme.BarlowCondensed
import com.adityrajiv.profileshowcase.presentation.theme.color.ProfileBlue

@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier,
    username: String,
    profilePhotoUrl: String,
    onUploadButtonClick: () -> Unit,
    onEditButtonClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        ProfilePhoto(
            modifier = Modifier.align(Alignment.Center),
            profilePhotoUrl = profilePhotoUrl
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .clickable {
                        onUploadButtonClick()
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.upload_icon),
                    contentDescription = stringResource(R.string.uploadButtonText)
                )
                Text(
                    text = stringResource(R.string.uploadButtonText),
                    fontFamily = BarlowCondensed,
                    color = ProfileBlue,
                    fontWeight = FontWeight.Light
                )
            }
            Column(
                modifier = Modifier
                    .padding(end = 26.dp)
                    .clickable {
                        onEditButtonClick()
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.edit_icon),
                    contentDescription = stringResource(R.string.editButtonText)
                )
                Text(
                    text = stringResource(R.string.editButtonText),
                    fontFamily = BarlowCondensed,
                    color = ProfileBlue,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = username,
            style = MaterialTheme.typography.displaySmall,
            fontFamily = Barlow
        )
    }
}

@Composable
private fun ProfilePhoto(modifier: Modifier = Modifier, profilePhotoUrl: String) {
    SubcomposeAsyncImage(
        modifier = modifier
            .size(127.dp)
            .clip(CircleShape),
        model = profilePhotoUrl,
        contentDescription = stringResource(R.string.profilePhotoContentDesc),
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
}

@Preview(showBackground = true)
@Composable
private fun ProfileMainHeaderPreview(modifier: Modifier = Modifier) {
    ProfileHeader(
        modifier = Modifier.fillMaxWidth(),
        username = "john.doe",
        profilePhotoUrl = PROFILE_URL,
        onUploadButtonClick = { },
        onEditButtonClick = { }
    )
}