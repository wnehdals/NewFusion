package com.jdm.app.main

import android.content.res.Resources
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.Navigator
import androidx.navigation.compose.rememberNavController
import com.jdm.app.designsystem.component.FusionSnackBar
import com.jdm.app.domain.model.SnackbarManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.net.UnknownHostException

@Composable
internal fun MainScreen(
) {
    val scaffoldState = rememberFusionScaffoldState()
    val navigator: NavHostController = rememberNavController()
    MainScaffold(
        modifier = Modifier
            .fillMaxSize(),
        snackBarHost = {
            SnackbarHost(
                hostState = it,
                modifier = Modifier
                    .systemBarsPadding()
                    .padding(bottom = 48.dp),
                snackbar = { snackbarData -> FusionSnackBar(snackbarData = snackbarData) }
            )
        },
        snackBarHostState = scaffoldState.snackBarHostState
    ) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            MainNavHost(
                navigator = navigator
            )
        }
    }
}

@Composable
fun MainScaffold(
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    snackBarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        snackbarHost = {
            snackBarHost(snackBarHostState)
        },
        containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
        contentColor = MaterialTheme.colorScheme.surface,
        content = content
    )

}

@Composable
fun rememberFusionScaffoldState(
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    snackBarManager: SnackbarManager = SnackbarManager,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): SdnScaffoldState = remember(snackBarHostState, snackBarManager, coroutineScope) {
    SdnScaffoldState(snackBarHostState, snackBarManager, coroutineScope)
}


@Stable
class SdnScaffoldState(
    val snackBarHostState: SnackbarHostState,
    private val snackBarManager: SnackbarManager,
    coroutineScope: CoroutineScope
) {
    init {
        coroutineScope.launch {
            snackBarManager.messages.collect { currentMessages ->
                if (currentMessages.isNotEmpty()) {
                    val message = currentMessages[0]
                    val text = message.text
                    snackBarManager.setMessageShown(message.id)
                    snackBarHostState.showSnackbar(text)
                }
            }
        }
    }
}