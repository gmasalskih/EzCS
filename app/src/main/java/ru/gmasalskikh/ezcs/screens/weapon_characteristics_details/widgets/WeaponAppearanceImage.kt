package ru.gmasalskikh.ezcs.screens.weapon_characteristics_details.widgets

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Deferred
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.data.type.TeamType
import ru.gmasalskikh.ezcs.ui.common_widget.ImageLoader

@Composable
fun WeaponAppearanceImage(
    modifier: Modifier = Modifier,
    deferredBitmap: Deferred<Bitmap>,
    teamTypes: List<TeamType>
) {
    Box(
        modifier = modifier
    ) {
        ImageLoader(
            modifier = Modifier
                .padding(32.dp),
            deferredBitmap = deferredBitmap
        )
        Row(
            modifier = Modifier.align(Alignment.BottomStart),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            teamTypes.forEach {
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
}