package com.jdm.app.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object NewsRoute : Route
}