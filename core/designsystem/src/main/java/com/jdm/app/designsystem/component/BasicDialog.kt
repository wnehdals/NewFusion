package com.jdm.app.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jdm.app.designsystem.theme.FusionTheme

@Composable
private fun BasicDialog(
    title: String,
    dialogProperties: DialogProperties = DialogProperties(),
    contents: @Composable (() -> Unit)? = null,
    buttonContents: @Composable RowScope.() -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        properties = dialogProperties,
        onDismissRequest = { onDismiss }
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = FusionTheme.colors.white
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(36.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp
                        ),
                    text = title,
                    style = FusionTheme.typography.title_s.copy(
                        textAlign = TextAlign.Center
                    )
                )
                contents?.let {
                    Spacer(modifier = Modifier.height(4.dp))
                    it.invoke()
                }

                Spacer(modifier = Modifier.height(36.dp))
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    buttonContents()
                }
            }
        }
    }
}

