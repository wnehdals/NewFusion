package com.jdm.app.domain.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

data class SanckbarToken(val id: Long, val text: String)

/**
 * Class responsible for managing Snackbar messages to show on the screen
 */
object SnackbarManager {

    private val _messages: MutableStateFlow<List<SanckbarToken>> = MutableStateFlow(emptyList())
    val messages: StateFlow<List<SanckbarToken>> get() = _messages.asStateFlow()

    fun showMessage(text: String) {
        _messages.update { currentMessages ->
            currentMessages + SanckbarToken(
                id = UUID.randomUUID().mostSignificantBits,
                text = text
            )
        }
    }

    fun setMessageShown(messageId: Long) {
        _messages.update { currentMessages ->
            currentMessages.filterNot { it.id == messageId }
        }
    }
}
