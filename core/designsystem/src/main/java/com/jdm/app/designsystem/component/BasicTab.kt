package com.jdm.app.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jdm.app.designsystem.theme.FusionTheme

@Composable
fun UnderLineTextTab(
    modifier: Modifier = Modifier,
    text: String,
    isChecked: Boolean,
    onClick: (Boolean) -> Unit,
    textStyle: TextStyle = FusionTheme.typography.title_s
) {

    Box(
        modifier = modifier
            .widthIn(min = 56.dp)
            .clickable { onClick(isChecked) },
    ) {
        Text(
            modifier = Modifier
                .align(
                    Alignment.Center
                ),
            text = text,
            style = textStyle,
            textAlign = TextAlign.Center,
            color = if (isChecked) FusionTheme.colors.green400 else FusionTheme.colors.gray300
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUnderLineTextTab() {
    FusionTheme {
        Row(
            modifier = Modifier.height(100.dp)
        ) {
            UnderLineTextTab(
                modifier = Modifier
                    .height(48.dp),
                text = "경제 지표",
                isChecked = true,
                onClick = {}
            )
            UnderLineTextTab(
                modifier = Modifier
                    .height(48.dp),
                text = "경제 지표",
                isChecked = false,
                onClick = {}
            )
        }

    }
}