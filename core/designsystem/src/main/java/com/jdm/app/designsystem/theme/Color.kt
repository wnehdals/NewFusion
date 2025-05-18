package com.jdm.app.designsystem.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color

val primaryLight = Color(0xFF805610)
val onPrimaryLight = Color(0xFFFFFFFF)
val primaryContainerLight = Color(0xFFFFDDB3)
val onPrimaryContainerLight = Color(0xFF291800)
val secondaryLight = Color(0xFF6F5B40)
val onSecondaryLight = Color(0xFFFFFFFF)
val secondaryContainerLight = Color(0xFFFBDEBC)
val onSecondaryContainerLight = Color(0xFF271904)
val tertiaryLight = Color(0xFF51643F)
val onTertiaryLight = Color(0xFFFFFFFF)
val tertiaryContainerLight = Color(0xFFD4EABB)
val onTertiaryContainerLight = Color(0xFF102004)
val errorLight = Color(0xFFBA1A1A)
val onErrorLight = Color(0xFFFFFFFF)
val errorContainerLight = Color(0xFFFFDAD6)
val onErrorContainerLight = Color(0xFF410002)
val backgroundLight = Color(0xFFFFF8F4)
val onBackgroundLight = Color(0xFF201B13)
val surfaceLight = Color(0xFFFFF8F4)
val onSurfaceLight = Color(0xFF201B13)
val surfaceVariantLight = Color(0xFFF0E0CF)
val onSurfaceVariantLight = Color(0xFF4F4539)
val outlineLight = Color(0xFF817567)
val outlineVariantLight = Color(0xFFD3C4B4)
val scrimLight = Color(0xFF000000)
val inverseSurfaceLight = Color(0xFF362F27)
val inverseOnSurfaceLight = Color(0xFFFCEFE2)
val inversePrimaryLight = Color(0xFFF4BD6F)
val surfaceDimLight = Color(0xFFE4D8CC)
val surfaceBrightLight = Color(0xFFFFF8F4)
val surfaceContainerLowestLight = Color(0xFFFFFFFF)
val surfaceContainerLowLight = Color(0xFFFFF1E5)
val surfaceContainerLight = Color(0xFFF9ECDF)
val surfaceContainerHighLight = Color(0xFFF3E6DA)
val surfaceContainerHighestLight = Color(0xFFEDE0D4)


val primaryDark = Color(0xFFF4BD6F)
val onPrimaryDark = Color(0xFF452B00)
val primaryContainerDark = Color(0xFF633F00)
val onPrimaryContainerDark = Color(0xFFFFDDB3)
val secondaryDark = Color(0xFFDDC2A1)
val onSecondaryDark = Color(0xFF3E2D16)
val secondaryContainerDark = Color(0xFF56442A)
val onSecondaryContainerDark = Color(0xFFFBDEBC)
val tertiaryDark = Color(0xFFB8CEA1)
val onTertiaryDark = Color(0xFF243515)
val tertiaryContainerDark = Color(0xFF3A4C2A)
val onTertiaryContainerDark = Color(0xFFD4EABB)
val errorDark = Color(0xFFFFB4AB)
val onErrorDark = Color(0xFF690005)
val errorContainerDark = Color(0xFF93000A)
val onErrorContainerDark = Color(0xFFFFDAD6)
val backgroundDark = Color(0xFF18120B)
val onBackgroundDark = Color(0xFFEDE0D4)
val surfaceDark = Color(0xFF18120B)
val onSurfaceDark = Color(0xFFEDE0D4)
val surfaceVariantDark = Color(0xFF4F4539)
val onSurfaceVariantDark = Color(0xFFD3C4B4)
val outlineDark = Color(0xFF9C8F80)
val outlineVariantDark = Color(0xFF4F4539)
val scrimDark = Color(0xFF000000)
val inverseSurfaceDark = Color(0xFFEDE0D4)
val inverseOnSurfaceDark = Color(0xFF362F27)
val inversePrimaryDark = Color(0xFF805610)
val surfaceDimDark = Color(0xFF18120B)
val surfaceBrightDark = Color(0xFF3F3830)
val surfaceContainerLowestDark = Color(0xFF120D07)
val surfaceContainerLowDark = Color(0xFF201B13)
val surfaceContainerDark = Color(0xFF251F17)
val surfaceContainerHighDark = Color(0xFF2F2921)
val surfaceContainerHighestDark = Color(0xFF3B342B)


@Stable
class FusionColors(
    white: Color,
    black: Color,
    gray100: Color,
    gray200: Color,
    gray300: Color,
    gray400: Color,
    gray500: Color,
    gray600: Color,
    gray700: Color,
    green300: Color,
    green400: Color,
    red400: Color,
    transparent: Color,
) {
    var white by mutableStateOf(white, structuralEqualityPolicy())
        internal  set
    var black by mutableStateOf(black, structuralEqualityPolicy())
        internal  set
    var gray100 by mutableStateOf(gray100, structuralEqualityPolicy())
        internal  set
    var gray200 by mutableStateOf(gray200, structuralEqualityPolicy())
        internal  set
    var gray300 by mutableStateOf(gray300, structuralEqualityPolicy())
        internal  set
    var gray400 by mutableStateOf(gray400, structuralEqualityPolicy())
        internal  set
    var gray500 by mutableStateOf(gray500, structuralEqualityPolicy())
        internal  set
    var gray600 by mutableStateOf(gray600, structuralEqualityPolicy())
        internal  set
    var gray700 by mutableStateOf(gray700, structuralEqualityPolicy())
        internal  set
    var green300 by mutableStateOf(green300, structuralEqualityPolicy())
        internal  set
    var green400 by mutableStateOf(green400, structuralEqualityPolicy())
        internal  set
    var red400 by mutableStateOf(red400, structuralEqualityPolicy())
        internal  set
    var transparent by mutableStateOf(transparent, structuralEqualityPolicy())
        internal  set

    fun copy(
        white: Color = this.white,
        black: Color = this.black,
        gray100: Color = this.gray100,
        gray200: Color = this.gray200,
        gray300: Color = this.gray300,
        gray400: Color = this.gray400,
        gray500: Color = this.gray500,
        gray600: Color = this.gray600,
        gray700: Color = this.gray700,
        green300: Color = this.green300,
        green400: Color = this.green400,
        red400: Color = this.red400,
        transparent: Color = this.transparent
    ): FusionColors = FusionColors(
        white, black, gray100, gray200, gray300, gray400, gray500, gray600, gray700, green300, green400, red400,transparent
    )
}

fun lightHdsColors(
    white: Color = Color(0xFFFFFFFF),
    black: Color = Color(0xFF000000),
    gray100: Color = Color(0xFFF8F8FB),
    gray200: Color = Color(0xFFECEDF2),
    gray300: Color = Color(0xFFD1D3DC),
    gray400: Color = Color(0xFFD5D6Dc),
    gray500: Color = Color(0xFFA2A8AD),
    gray600: Color = Color(0xFF5E6164),
    gray700: Color = Color(0xFF333336),
    green300: Color = Color(0xFFF1FBFA),
    green400: Color = Color(0xFF1A998E),
    red400: Color = Color(0xFFE81717),
    transparent : Color= Color(0x00000000)
): FusionColors = FusionColors(
    white, black, gray100, gray200, gray300, gray400, gray500, gray600, gray700, green300, green400, red400, transparent
)

internal val LocalColors = staticCompositionLocalOf { lightHdsColors() }