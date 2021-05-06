package ru.gmasalskikh.ezcs.screens.weapon_characteristics_details.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.gmasalskikh.ezcs.ui.theme.fontSize16Sp
import ru.gmasalskikh.ezcs.utils.LocalAppTheme
import java.util.*

@Composable
fun WeaponDetailsItem(
    title: String,
    value: String
){
    val theme = LocalAppTheme.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(theme.colors.background)
    ) {
        LinearProgressIndicator(
            progress = 0.3f,
            color = theme.colors.primary,
            backgroundColor = theme.colors.background,
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(5.dp),
            text = title.toUpperCase(Locale.getDefault()),
            fontSize = fontSize16Sp
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(5.dp),
            text = value,
            fontSize = fontSize16Sp
        )
        Divider()
    }
}