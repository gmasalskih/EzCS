package ru.gmasalskikh.ezcs.screens.weapon_characteristics.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.gmasalskikh.ezcs.data.app_entity.Weapon
import ru.gmasalskikh.ezcs.data.type.TeamType
import ru.gmasalskikh.ezcs.ui.common_widget.ImageLoader
import ru.gmasalskikh.ezcs.utils.LocalAppTheme
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.weapon_characteristics.WeaponCharacteristicsViewEvent
import ru.gmasalskikh.ezcs.utils.bitmapFromResources

@Composable
fun WeaponListItem(
    weapon: Weapon,
    isSelected: Boolean
) {
    val theme = LocalAppTheme.current
    Box(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = theme.colors.primary,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(5.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.BottomStart),
            text = weapon.name
        )
        ImageLoader(
            modifier = Modifier
                .height(70.dp)
                .align(Alignment.Center),
            deferredBitmap = weapon.logoDeferred,
            contentScale = ContentScale.FillHeight
        )
        Row(
            modifier = Modifier.align(Alignment.TopEnd),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            weapon.teamTypes.forEach {
                when (it) {
                    TeamType.T -> R.drawable.icon_t
                    TeamType.CT -> R.drawable.icon_ct
                }.let { drawableRes ->
                    Image(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = drawableRes),
                        contentDescription = null
                    )
                }
            }
        }
        Image(
            modifier = Modifier
                .height(20.dp)
                .align(Alignment.BottomEnd),
            painter = painterResource(id = R.drawable.icon_add_to_compare),
            contentDescription = null,
            colorFilter = ColorFilter.tint(if (isSelected) theme.colors.primary else theme.colors.secondary)
        )
    }

}