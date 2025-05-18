package com.jdm.app.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jdm.app.core.designsystem.R
import com.jdm.app.designsystem.theme.FusionTheme

@Composable
fun UpDownArrowButton(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    ImageToggleButton(
        modifier = modifier,
        checked = checked,
        onCheckedChange = onCheckedChange,
        checkedRes = R.drawable.ic_arrow_up_black,
        unCheckedRes = R.drawable.ic_arrow_down_black
    )
}

@Composable
internal fun ImageToggleButton(
    modifier: Modifier = Modifier,
    checked: Boolean,
    @DrawableRes checkedRes: Int,
    @DrawableRes unCheckedRes: Int,
    onCheckedChange: (Boolean) -> Unit,
    content: @Composable (RowScope.() -> Unit)? = null
) {
    Row(
        modifier = modifier.clickable {
            onCheckedChange(!checked)
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (checked) {
            StableImage(drawableResId = checkedRes, contentDescription = "check checkbox", onClick = {onCheckedChange(!checked)})
        } else {
            StableImage(drawableResId = unCheckedRes, contentDescription = "uncheck checkbox", onClick = {onCheckedChange(!checked)})
        }
        if (content != null) {
            Spacer(modifier = Modifier.width(8.dp))
            content()
        }
    }
}
@Composable
internal fun StableImage (
    modifier: Modifier = Modifier
        .background(color = FusionTheme.colors.transparent)
        .size(16.dp),
    @DrawableRes drawableResId: Int,
    contentDescription : String,
    onClick: () -> Unit
) {
    val painter = painterResource(id = drawableResId)
    Image(
        modifier = modifier
            .clickable {
                onClick.invoke()
            },
        painter = painter,
        contentDescription = contentDescription
    )
}