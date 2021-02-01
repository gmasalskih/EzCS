package ru.gmasalskikh.ezcs.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import ru.gmasalskikh.ezcs.R

val verdana_regular = FontFamily(
    fonts = listOf(
        Font(R.font.verdana_regular)
    )
)

val verdana_bold = FontFamily(
    fonts = listOf(
        Font(R.font.verdana_bold)
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
//    defaultFontFamily = verdana_regular,
)