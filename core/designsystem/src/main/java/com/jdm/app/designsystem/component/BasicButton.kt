package com.jdm.app.designsystem.component

import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jdm.app.designsystem.theme.FusionTheme
import com.jdm.app.designsystem.theme.FusionTypography


@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    isEnable: Boolean = true
) {
    BasicPrimaryButton(
        modifier = modifier,
        onClick = onClick,
        isEnable = isEnable,
        contents = {
            Text(
                text = text,
                style = FusionTheme.typography.title_s.copy(color = if (isEnable) FusionTheme.colors.white else FusionTheme.colors.gray500),
            )
        }
    )
}


@Composable
fun SecondButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    isEnable: Boolean = true
) {

    BasicSecondButton(
        modifier = modifier,
        onClick = onClick,
        isEnable = isEnable,
        contents = {
            Text(
                text = text,
                style = FusionTheme.typography.title_s.copy(color = if (isEnable) FusionTheme.colors.green400 else FusionTheme.colors.gray500),
            )
        }
    )
}


@Composable
internal fun BasicPrimaryButton(
    modifier: Modifier = Modifier,
    isEnable: Boolean = true,
    contents: @Composable RowScope.() -> Unit,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
        onClick = onClick,
        shape = RoundedCornerShape(4.dp),
        enabled = isEnable,
        colors = ButtonDefaults.buttonColors(
            containerColor = FusionTheme.colors.green400,
            contentColor = FusionTheme.colors.white,
            disabledContainerColor = FusionTheme.colors.gray300,
            disabledContentColor = FusionTheme.colors.gray600
        ),
        contentPadding = PaddingValues(
            horizontal = 0.dp,
            vertical = 0.dp
        )
    ) {
        contents()
    }
}

@Composable
internal fun BasicSecondButton(
    modifier: Modifier = Modifier,
    isEnable: Boolean = true,
    contents: @Composable RowScope.() -> Unit,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
        onClick = onClick,
        shape = RoundedCornerShape(4.dp),
        enabled = isEnable,
        colors = ButtonDefaults.buttonColors(
            containerColor = FusionTheme.colors.green300,
            contentColor = FusionTheme.colors.white,
            disabledContainerColor = FusionTheme.colors.gray300,
            disabledContentColor = FusionTheme.colors.gray600
        ),
        contentPadding = PaddingValues(
            horizontal = 0.dp,
            vertical = 0.dp
        )
    ) {
        contents()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewButtons(

) {
    FusionTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 20.dp
                )
        ) {
            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                text = "확인",
                onClick = {}
            )
            Spacer(
                modifier = Modifier
                .height(20.dp)
            )
            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                text = "확인",
                isEnable = false,
                onClick = {}
            )
            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )
            SecondButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                text = "확인",
                onClick = {}
            )
            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )
            SecondButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                text = "확인",
                isEnable = false,
                onClick = {}
            )
            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )
        }
    }
}