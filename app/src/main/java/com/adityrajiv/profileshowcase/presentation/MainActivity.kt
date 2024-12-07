package com.adityrajiv.profileshowcase.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.adityrajiv.profileshowcase.R
import com.adityrajiv.profileshowcase.presentation.profile.ProfileViewModel
import com.adityrajiv.profileshowcase.presentation.profile.ProfileScreen
import com.adityrajiv.profileshowcase.presentation.profile.util.PALETTE_URL
import com.adityrajiv.profileshowcase.presentation.profile.util.PROFILE_URL
import com.adityrajiv.profileshowcase.presentation.profile.util.ProfileStatItem
import com.adityrajiv.profileshowcase.presentation.theme.ProfileShowcaseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfileShowcaseTheme(
                dynamicColor = false
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val profileViewModel: ProfileViewModel = hiltViewModel()
                    val uploadsState by profileViewModel.uploadsState.collectAsStateWithLifecycle()
                    ProfileScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .statusBarsPadding()
                            .padding(horizontal = 14.dp),
                        username = profileViewModel.username,
                        profilePhotoUrl = profileViewModel.profilePhotoUrl,
                        paletteImageUrl = profileViewModel.paletteImageUrl,
                        uploadsState = uploadsState,
                        primaryStatItems = profileViewModel.primaryStatItems,
                        secondaryStatItems = profileViewModel.secondaryStatItems,
                        onUserButtonClick = { },
                        onCreateButtonClick = { },
                        onDrawerButtonClick = { },
                        onUploadButtonClick = { },
                        onEditButtonClick = { },
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    val mockUploads = listOf(
        "https://via.placeholder.com/600/92c952",
        "https://via.placeholder.com/600/323599"
    )
    val mockPrimaryStats = listOf(
        ProfileStatItem(
            metric = 2300,
            label = "Followers",
            iconId = null
        ),
        ProfileStatItem(
            metric = 50,
            label = "Artworks",
            iconId = null
        ),
        ProfileStatItem(
            metric = 21,
            label = "Exhibitions",
            iconId = null
        )
    )

    val mockSecondaryStats = listOf(
        ProfileStatItem(
            metric = 120,
            label = "Likes",
            iconId = R.drawable.like_icon,
            iconTint = Color(0xFFFF0000)
        ),
        ProfileStatItem(
            metric = 43000,
            label = "Interactions",
            iconId = R.drawable.cursor_icon,
            iconTint = Color(0xFF007DB2)
        ),
        ProfileStatItem(
            metric = 2300,
            label = "Shares",
            iconId = R.drawable.share_icon
        )
    )

    ProfileScreen(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 14.dp),
        username = "john.doe",
        profilePhotoUrl = PROFILE_URL,
        paletteImageUrl = PALETTE_URL,
        uploadsState = ProfileViewModel.UploadsState.Success(mockUploads),
        primaryStatItems = mockPrimaryStats,
        secondaryStatItems = mockSecondaryStats,
        onUserButtonClick = { },
        onCreateButtonClick = { },
        onDrawerButtonClick = { },
        onUploadButtonClick = { },
        onEditButtonClick = { },
    )
}