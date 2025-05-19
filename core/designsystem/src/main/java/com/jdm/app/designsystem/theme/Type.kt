package com.jdm.app.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp


private val SansSerifStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Normal,
    color = Color.Black,
)

private val SansSerifTitleStyle = SansSerifStyle.copy(
    fontWeight = FontWeight.Bold,
)

private val SansSerifTextStyle = SansSerifStyle.copy(
    fontWeight = FontWeight.Normal,
)


@Immutable
data class FusionTypography(
    val title_xl: TextStyle,
    val title_l: TextStyle,
    val title_m: TextStyle,
    val title_s: TextStyle,
    val title_xs: TextStyle,
    val title_xxs: TextStyle,

    val text_xl: TextStyle,
    val text_l: TextStyle,
    val text_m: TextStyle,
    val text_s: TextStyle,
    val text_xs: TextStyle,
    val text_xxs: TextStyle,
)



internal val mobileTypography = FusionTypography(
    title_xl = SansSerifTitleStyle.copy(
        fontSize = 32.sp,
    ),
    title_l = SansSerifTitleStyle.copy(
        fontSize = 28.sp,
    ),
    title_m = SansSerifTitleStyle.copy(
        fontSize = 24.sp,
    ),
    title_s = SansSerifTitleStyle.copy(
        fontSize = 20.sp,
    ),
    title_xs = SansSerifTitleStyle.copy(
        fontSize = 16.sp,
    ),
    title_xxs = SansSerifTitleStyle.copy(
        fontSize = 12.sp,
    ),
    text_xl = SansSerifTextStyle.copy(
        fontSize = 20.sp,
    ),
    text_l = SansSerifTextStyle.copy(
        fontSize = 18.sp,
    ),
    text_m = SansSerifTextStyle.copy(
        fontSize = 16.sp,
    ),
    text_s = SansSerifTextStyle.copy(
        fontSize = 14.sp,
    ),
    text_xs = SansSerifTextStyle.copy(
        fontSize = 12.sp,
    ),
    text_xxs = SansSerifTextStyle.copy(
        fontSize = 10.sp,
    ),
)
internal val LocalTypography = staticCompositionLocalOf {
    mobileTypography
}