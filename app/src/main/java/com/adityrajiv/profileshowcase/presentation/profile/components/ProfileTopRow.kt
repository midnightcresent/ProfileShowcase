package com.adityrajiv.profileshowcase.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.adityrajiv.profileshowcase.R
import com.adityrajiv.profileshowcase.presentation.theme.color.UserIconRed

@Composable
fun ProfileTopBar(
    modifier: Modifier = Modifier,
    onUserButtonClick: () -> Unit,
    onCreateButtonClick: () -> Unit,
    onDrawerButtonClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.logo_header),
            contentDescription = stringResource(R.string.brandContentDesc)
        )
        ActionsRow(
            onUserButtonClick = onUserButtonClick,
            onCreateButtonClick = onCreateButtonClick,
            onDrawerButtonClick = onDrawerButtonClick
        )
    }
}

@Composable
private fun ActionsRow(
    modifier: Modifier = Modifier,
    onUserButtonClick: () -> Unit,
    onCreateButtonClick: () -> Unit,
    onDrawerButtonClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileLogo(
            modifier = Modifier.padding(end = 14.dp),
            onClick = onUserButtonClick
        )
        IconButton(onClick = onCreateButtonClick) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.add_icon),
                contentDescription = stringResource(R.string.addIconContentDesc)
            )
        }
        IconButton(onClick = onDrawerButtonClick) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.drawer_icon),
                contentDescription = stringResource(R.string.drawerIconContentDesc)
            )
        }
    }
}

@Composable
private fun ProfileLogo(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(33.dp)
                .background(UserIconRed, shape = CircleShape)
        )
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.user_icon),
            contentDescription = stringResource(R.string.userIconContentDesc),
            tint = Color.White
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun ProfileTopRowPreview() {
//    ProfileTopBar(modifier = Modifier.fillMaxWidth())
//}
