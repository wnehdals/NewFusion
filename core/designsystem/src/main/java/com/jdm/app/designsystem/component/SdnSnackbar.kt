package com.jdm.app.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jdm.app.designsystem.theme.FusionTheme

@Composable
fun FusionSnackBar(
    modifier: Modifier = Modifier,
    snackbarData: SnackbarData,
    stroke: Dp = 1.dp,
    strokeColor: Color = FusionTheme.colors.gray500,
    containerColor: Color = FusionTheme.colors.gray500,
    textColor: Color = FusionTheme.colors.white,
    yOffset: Dp = 56.dp,
    @DrawableRes actionIcon: Int? = null,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .background(FusionTheme.colors.transparent)

    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(stroke, strokeColor),
            color = containerColor
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    actionIcon?.let {
                        Image(
                            painter = painterResource(
                                id = it)
                            ,
                            contentDescription = "toast icon"
                        )
                        Spacer(
                            modifier = Modifier.width(4.dp)
                        )
                    }
                    Text(
                        modifier = Modifier
                            .padding(
                                vertical = 12.dp
                            ),
                        text = snackbarData.visuals.message,
                        style = FusionTheme.typography.text_m.copy(
                            color = textColor,
                            textAlign = TextAlign.Center
                        ),
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(yOffset))
    }
}