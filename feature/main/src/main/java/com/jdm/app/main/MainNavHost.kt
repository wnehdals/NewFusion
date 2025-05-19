package com.jdm.app.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.jdm.app.navigation.Route
import com.jdm.app.news.newsNavGraph

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navigator: NavHostController,
) {
    NavHost(
        modifier = Modifier
            .fillMaxSize(),
        navController = navigator,
        startDestination = Route.NewsRoute
    ) {
        newsNavGraph(
            onBack = {
                navigator.popBackStack()
            }
        )
    }
}