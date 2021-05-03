package ru.gmasalskikh.ezcs.screens.weapon_characteristics_details.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.ui.theme.fontSize16Sp
import ru.gmasalskikh.ezcs.utils.LocalAppTheme
import java.util.*

@Composable
fun WeaponDetails(
    modifier: Modifier = Modifier,
    weaponDetails: List<Pair<String, String>>
){
    val theme = LocalAppTheme.current
    Column(
        modifier = modifier
            .border(
                width = 1.dp,
                color = theme.colors.primary,
                shape = RoundedCornerShape(5.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(theme.colors.secondary)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(5.dp),
                text = stringResource(
                    id = R.string.app_top_bar_title_weapon_characteristics
                ).toUpperCase(Locale.getDefault()),
                color = theme.colors.primary,
                fontSize = fontSize16Sp
            )
        }
        weaponDetails.forEach {
            WeaponDetailsItem(title = it.first, value = it.second)
            Divider()
        }
    }
}