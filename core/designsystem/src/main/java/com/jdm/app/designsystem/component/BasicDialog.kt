package com.jdm.app.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jdm.app.designsystem.theme.FusionTheme



@Composable
fun ConfirmDialog(
    title: String,
    dialogProperties: DialogProperties = DialogProperties(),
    rightText: String,
    onDismiss: () -> Unit,
    onClickButton: () -> Unit
) {
    BasicDialog(
        title = title,
        dialogProperties = dialogProperties,
        onDismiss = onDismiss,
        buttonContents = {
            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                onClick = onClickButton,
                text = rightText
            )
        }
    )
}

@Composable
internal fun BasicDialog(
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    buttonContents()
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewConfirmDialog(

) {
    FusionTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 20.dp
                )
        ) {
            ConfirmDialog(
                title = "오류",
                rightText = "확인",
                onDismiss = {},
                onClickButton = {}
            )
        }
    }
}

