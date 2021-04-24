package ru.gmasalskikh.ezcs.screens.weapon_characteristics_details.widgets

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.data.app_entity.Weapon
import ru.gmasalskikh.ezcs.data.type.TeamType
import ru.gmasalskikh.ezcs.ui.common_widget.ImageLoader
import ru.gmasalskikh.ezcs.ui.theme.fontSize16Sp
import ru.gmasalskikh.ezcs.utils.LocalAppTheme
import java.util.*

@Composable
fun WeaponCharacteristicsDetails(weapon: Weapon) {
    val theme = LocalAppTheme.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
//                .weight(1f)
        ) {
            ImageLoader(
                modifier = Modifier
                    .padding(32.dp),
                deferredBitmap = weapon.logoDeferred
            )
            Row(
                modifier = Modifier.align(Alignment.BottomStart),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                weapon.teamTypes.forEach {
                    when (it) {
                        TeamType.T -> R.drawable.icon_t
                        TeamType.CT -> R.drawable.icon_ct
                    }.let { drawableRes ->
                        Image(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = drawableRes),
                            contentDescription = null
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .height(200.dp)
//                .weight(1f)
                .border(
                    width = 1.dp,
                    color = theme.colors.primary,
                    shape = RoundedCornerShape(5.dp)
                )
        ) {
            ImageLoader(
                modifier = Modifier
                    .weight(1f),
                deferredBitmap = weapon.sprayDeferred,
                contentScale = ContentScale.FillBounds
            )
            ImageLoader(
                modifier = Modifier
                    .weight(1f),
                deferredBitmap = weapon.recoilDeferred,
                contentScale = ContentScale.FillBounds
            )
        }
        Column(
            modifier = Modifier
                .verticalScroll(state = ScrollState(0))
                .weight(1f)
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
            // TODO: 24.04.2021 title move to resource
            BoxItem(title = "COST:", value = weapon.inGamePrice.toString() + "$")
            BoxItem(
                title = "AMMO:", value = weapon.primaryClipSize.toString() +
                        "/" +
                        weapon.primaryReserveAmmoMax.toString()
            )
            BoxItem(title = "KILL AWARD:", value = weapon.killAward.toString() + "$")
            BoxItem(title = "DAMAGE:", value = weapon.damage.toString())
            BoxItem(title = "FIRE RATE:", value = "???")
            // TODO: 24.04.2021 раскрыть в линию с иконками (отдача по оси Х, отдача по оси Y)
            BoxItem(title = "RECOIL CONTROL:", value = weapon.recoilMagnitude.toString())
            // TODO: 24.04.2021 раскрыть в линью с иконками (неточност сидя, нетосность в движении, неточность стоя)
            BoxItem(title = "ACCURATE RANGE:", value = weapon.inaccuracyCrouch.toString())
            // TODO: 24.04.2021 раскрыть в линию с иконками (бронепробиваемость противника, пробивание укрытия)
            BoxItem(title = "ARMOR PENETRATION:", value = weapon.penetration.toString())
        }
    }
}

@Composable
fun BoxItem(title: String, value: String) {
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