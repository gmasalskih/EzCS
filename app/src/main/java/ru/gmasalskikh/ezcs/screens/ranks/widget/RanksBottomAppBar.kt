package ru.gmasalskikh.ezcs.screens.ranks.widget

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage
import ru.gmasalskikh.ezcs.data.model.ranks.RankBottomAppBarItem
import ru.gmasalskikh.ezcs.data.model.ranks.RanksBottomAppBarItemType
import ru.gmasalskikh.ezcs.providers.mapper.ResourceMapper
import ru.gmasalskikh.ezcs.ui.theme.fontSize10Sp
import ru.gmasalskikh.ezcs.ui.theme.fontSize12Sp
import java.util.*

@Composable
fun RanksBottomAppBar(
    modifier: Modifier = Modifier,
    onBottomBarItemClick: (RanksBottomAppBarItemType) -> Unit,
    itemsBackgroundColor: Color,
    activeItemColor: Color,
    passiveItemColor: Color,
    list: List<RankBottomAppBarItem>,
    currentSelectedItem: RanksBottomAppBarItemType,
    resourceMapper: ResourceMapper,
) {
    Row(
        modifier = Modifier.then(modifier)
    ) {
        list.forEach { item ->
            RanksBottomAppBarItem(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                label = stringResource(id = resourceMapper.getStringRes(item.type)),
                iconRes = resourceMapper.getDrawableRes(item.type),
                backGroundColor = itemsBackgroundColor,
                contentColor = if (currentSelectedItem == item.type) activeItemColor else passiveItemColor,
                onClick = { onBottomBarItemClick(item.type) }
            )
        }
    }
}

@Composable
private fun RanksBottomAppBarItem(
    modifier: Modifier = Modifier,
    label: String,
    @DrawableRes
    iconRes: Int,
    backGroundColor: Color,
    contentColor: Color,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        color = backGroundColor
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 2.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CoilImage(
                    modifier = Modifier.size(35.dp),
                    data = iconRes,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(contentColor),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = label.toUpperCase(Locale.getDefault()),
                    textAlign = TextAlign.Center,
                    fontSize = fontSize12Sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = contentColor
                )
            }
        }
    }
}