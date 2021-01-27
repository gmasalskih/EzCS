package ru.gmasalskikh.ezcs.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.TextUnit
import ru.gmasalskikh.ezcs.R

val Verdana = fontFamily(
    fonts = listOf(
        font(R.font.verdana_regular)
    )
)

val fontSize8Sp = TextUnit.Sp(8)
val fontSize10Sp = TextUnit.Sp(10)
val fontSize12Sp = TextUnit.Sp(10)
val fontSize14Sp = TextUnit.Sp(14)
val fontSize16Sp = TextUnit.Sp(16)
val fontSize18Sp = TextUnit.Sp(18)
val fontSize20Sp = TextUnit.Sp(20)

val EzCSTypography = Typography(
    defaultFontFamily = Verdana,
)

