package com.jdm.app.news

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jdm.app.navigation.Route

fun NavGraphBuilder.newsNavGraph(
    onBack: () -> Unit,
) {
    composable<Route.NewsRoute> {
        NewsRoute(
            onBack = onBack,
        )
    }
}