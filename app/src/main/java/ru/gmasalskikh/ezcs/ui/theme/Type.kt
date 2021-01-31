package ru.gmasalskikh.ezcs.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.sp
import ru.gmasalskikh.ezcs.R

val Verdana = fontFamily(
    fonts = listOf(
        font(R.font.verdana_regular)
    )
)

val fontSize8Sp = 8.sp
val fontSize10Sp = 10.sp
val fontSize12Sp = 12.sp
val fontSize14Sp = 14.sp
val fontSize16Sp = 16.sp
val fontSize18Sp = 18.sp
val fontSize20Sp = 20.sp

val EzCSTypography = Typography(
    defaultFontFamily = Verdana,
)