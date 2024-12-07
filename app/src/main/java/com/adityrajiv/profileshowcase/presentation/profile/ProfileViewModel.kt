package com.adityrajiv.profileshowcase.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adityrajiv.profileshowcase.R
import com.adityrajiv.profileshowcase.data.repository.ImagePostApi
import com.adityrajiv.profileshowcase.presentation.profile.util.PALETTE_URL
import com.adityrajiv.profileshowcase.presentation.profile.util.PROFILE_URL
import com.adityrajiv.profileshowcase.presentation.profile.util.ProfileStatItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val imagePostApi: ImagePostApi
): ViewModel() {

    private val _uploadUrls = MutableStateFlow<List<String>>(emptyList())
    val uploadUrls = _uploadUrls.asStateFlow() // Exposes the upload URLs as an immutable StateFlow for UI updates.

    val username by mutableStateOf(
        "john.doe"
    )

    val profilePhotoUrl by mutableStateOf(
        PROFILE_URL
    )

    val paletteImageUrl by mutableStateOf(
        PALETTE_URL
    )

    val primaryStatItems = listOf(
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

    val secondaryStatItems = listOf(
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

    init {
        viewModelScope.launch(Dispatchers.IO) { // Launches a coroutine in the ViewModel's scope on the IO dispatcher for background tasks.
            imagePostApi.getImagePosts(
                page = 1,
                limit = 10
            ).body()?.let { imagePosts ->
                val urls = imagePosts.map { it.url } // Collect URLs from the response
                _uploadUrls.emit(urls) // Emit the URLs to the StateFlow
            }
        }
    }
}