package ru.gmasalskikh.ezcs.screens.weapon_characteristics_details.widgets

import android.graphics.Bitmap
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Deferred
import ru.gmasalskikh.ezcs.ui.common_widget.ImageLoader
import ru.gmasalskikh.ezcs.utils.LocalAppTheme

@Composable
fun WeaponRecoilDiagram(
    modifier: Modifier = Modifier,
    sprayDeferred: Deferred<Bitmap>,
    recoilDeferred: Deferred<Bitmap>
){
    val theme = LocalAppTheme.current
    Row(
        modifier = modifier
            .border(
                width = 1.dp,
                color = theme.colors.primary,
                shape = RoundedCornerShape(5.dp)
            )
    ) {
        ImageLoader(
            modifier = Modifier
                .weight(1f),
            deferredBitmap = sprayDeferred,
            contentScale = ContentScale.FillBounds
        )
        ImageLoader(
            modifier = Modifier
                .weight(1f),
            deferredBitmap = recoilDeferred,
            contentScale = ContentScale.FillBounds
        )
    }
}