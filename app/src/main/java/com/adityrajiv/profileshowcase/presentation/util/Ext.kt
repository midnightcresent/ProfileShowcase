package com.adityrajiv.profileshowcase.presentation.util

import java.util.Locale

fun convertToKs(value: Int): String {
    return when {
        value >= 1_000_000 -> String.format(Locale.ROOT, "%.1fM", value / 1_000_000.0)
        value >= 1_000 -> {
            val result = value / 1_000.0
            if (result % 1.0 == 0.0) {
                String.format(Locale.ROOT, "%.0fK", result) // No decimals if whole number
            } else {
                String.format(Locale.ROOT, "%.1fK", result) // One decimal place otherwise
            }
        }
        else -> value.toString()
    }
}