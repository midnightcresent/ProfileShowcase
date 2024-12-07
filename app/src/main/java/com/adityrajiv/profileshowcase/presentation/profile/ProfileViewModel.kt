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
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val imagePostApi: ImagePostApi
) : ViewModel() {

    sealed class UploadsState {
        data object Loading : UploadsState()
        data class Success(val urls: List<String>) : UploadsState()
        data class Error(val message: String) : UploadsState()
    }

    private val _uploadsState = MutableStateFlow<UploadsState>(UploadsState.Loading)
    val uploadsState = _uploadsState.asStateFlow()

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

    // Coroutine exception handler for comprehensive error tracking
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _uploadsState.update {
            UploadsState.Error(
                message = throwable.message ?: "An unexpected error occurred"
            )
        }
    }

    // Function to fetch image posts
    private fun fetchImagePosts(page: Int = 1, limit: Int = 10) {
        // Reset to loading state before starting a new network call
        _uploadsState.value = UploadsState.Loading

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            try {
                val response = imagePostApi.getImagePosts(page, limit)

                if (response.isSuccessful) {
                    val result = response.body()
                    if (result != null) {
                        val urls = result.map { it.url }
                        _uploadsState.update { UploadsState.Success(urls) }
                    } else {
                        _uploadsState.update {
                            UploadsState.Error("No data received from the server")
                        }
                    }
                } else {
                    _uploadsState.update {
                        UploadsState.Error("Server error: ${response.code()} - ${response.message()}")
                    }
                }
            } catch (e: Exception) {
                _uploadsState.update {
                    UploadsState.Error(
                        message = e.message ?: "Network connection error"
                    )
                }
            }
        }
    }

    init {
        fetchImagePosts()
    }
}
