package com.jdm.app.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jdm.app.designsystem.theme.FusionTheme

@Composable
fun OutlineTextChip(
    modifier: Modifier = Modifier,
    text: String,
    isChecked: Boolean,
    onClick: (Boolean) -> Unit,
    verticalPadding: Dp = 4.dp,
    horizontalPadding: Dp = 12.dp,
    textStyle: TextStyle = FusionTheme.typography.text_xxs,
    checkedTextColor: Color = FusionTheme.colors.white,
    unCheckedTextColor: Color = FusionTheme.colors.black,
    strokeColor: Color = FusionTheme.colors.gray300,
    containerColor: Color = FusionTheme.colors.white,
) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(8.dp),
                color = if (isChecked) FusionTheme.colors.green400 else strokeColor
            )
            .background(
                shape = RoundedCornerShape(8.dp),
                color =  if (isChecked) FusionTheme.colors.green400 else containerColor
            )
            .padding(
                vertical = verticalPadding,
                horizontal = horizontalPadding
            ).clickable {
                onClick(isChecked)
            }
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = text,
            style = textStyle,
            color = if (isChecked) checkedTextColor else unCheckedTextColor
        )
    }
}



@Preview(showBackground = true)
@Composable
fun OutlineTextChipPreview() {
    FusionTheme {
        Column(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
        ) {
            OutlineTextChip(
                modifier = Modifier,
                text = "경제 발표",
                isChecked = false,
                onClick = {}
            )
            OutlineTextChip(
                modifier = Modifier,
                text = "경제 발표",
                isChecked = true,
                onClick = {}
            )
        }

    }
}
