package ru.gmasalskikh.ezcs.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import ru.gmasalskikh.ezcs.R


val VerdanaRegular = fontFamily(
    fonts = listOf(
        font(R.font.verdana_regular)
    )
)

val VerdanaBold = fontFamily(
    fonts = listOf(
        font(R.font.verdana_bold),
    )
)

val fontSize8Sp = TextUnit.Sp(8)
val fontSize10Sp = TextUnit.Sp(10)
val fontSize12Sp = TextUnit.Sp(10)
val fontSize14Sp = TextUnit.Sp(14)
val fontSize16Sp = TextUnit.Sp(16)
val fontSize18Sp = TextUnit.Sp(18)
val fontSize20Sp = TextUnit.Sp(20)

// Set of Material typography styles to start with
val typography = Typography(
    body1 = TextStyle(
        fontFamily = VerdanaRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
button = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W500,
    fontSize = 14.sp
),
caption = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp
)
*/
)

