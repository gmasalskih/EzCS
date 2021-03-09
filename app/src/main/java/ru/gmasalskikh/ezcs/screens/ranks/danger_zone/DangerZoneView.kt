package ru.gmasalskikh.ezcs.screens.ranks.danger_zone

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.screens.ranks.danger_zone.widgets.DangerZoneItem
import ru.gmasalskikh.ezcs.utils.LocalAppTheme

class DangerZoneView(
    vm: DangerZoneViewModel
) : BaseView<DangerZoneViewState, Nothing, DangerZoneViewModel>(vm) {

    @Composable
    override fun SetContent(viewState: DangerZoneViewState) {
        val theme = LocalAppTheme.current
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(viewState.items.sortedBy { it.order }) { dangerZone ->
                DangerZoneItem(
                    colorProgressIndicator = theme.colors.primary,
                    name = dangerZone.name,
                    contentDescription = dangerZone.logoDescription,
                    deferredBitmap = dangerZone.logoDeferred
                )
            }
        }
    }
}