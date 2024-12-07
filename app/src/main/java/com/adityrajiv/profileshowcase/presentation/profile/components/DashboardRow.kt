package com.adityrajiv.profileshowcase.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.adityrajiv.profileshowcase.R
import com.adityrajiv.profileshowcase.presentation.theme.BarlowCondensed

@Composable
fun DashboardRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.dashboardText),
            fontFamily = BarlowCondensed,
            fontWeight = FontWeight.Light
        )
        var isChecked by remember { mutableStateOf(true) }

        MaterialTwoSwitch(
            checked = isChecked,
            onCheckedChange = { isChecked = !isChecked }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardRowPreview(modifier: Modifier = Modifier) {
    DashboardRow(modifier = Modifier.fillMaxWidth())
}