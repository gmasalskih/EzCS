package ru.gmasalskikh.ezcs.ui.common_widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun <T> ListContent(
    isScrollableList: Boolean = false,
    listItemSurfaceColor: Color,
    listItemElevation: Dp,
    listItemShape: CornerBasedShape,
    listItemBorder: BorderStroke,
    onListItemClick: (T) -> Unit,
    items: List<T>,
    itemContent: @Composable (T) -> Unit
) {
    if (isScrollableList) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(items) { item ->
                ListItem(
                    modifier = Modifier.aspectRatio(3f),
                    backgroundColor = listItemSurfaceColor,
                    elevation = listItemElevation,
                    shape = listItemShape,
                    border = listItemBorder,
                    onClick = { onListItemClick(item) }
                ) {
                    itemContent(item)
                }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items.forEach { item ->
                ListItem(
                    modifier = Modifier.weight(1f),
                    backgroundColor = listItemSurfaceColor,
                    elevation = listItemElevation,
                    shape = listItemShape,
                    border = listItemBorder,
                    onClick = { onListItemClick(item) }
                ) {
                    itemContent(item)
                }
            }
        }
    }
}