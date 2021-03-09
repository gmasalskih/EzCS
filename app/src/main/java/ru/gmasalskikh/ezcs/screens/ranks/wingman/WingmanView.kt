package ru.gmasalskikh.ezcs.screens.ranks.wingman

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
import ru.gmasalskikh.ezcs.screens.ranks.wingman.widgets.WingmanItem
import ru.gmasalskikh.ezcs.utils.LocalAppTheme

class WingmanView(
    vm: WingmanViewModel
) : BaseView<WingmanViewState, Nothing, WingmanViewModel>(vm) {

    @Composable
    override fun SetContent(viewState: WingmanViewState) {
        val theme = LocalAppTheme.current
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(viewState.items.sortedBy { it.order }) { wingman ->
                WingmanItem(
                    colorProgressIndicator = theme.colors.primary,
                    name = wingman.name,
                    contentDescription = wingman.logoDescription,
                    deferredBitmap = wingman.logoDeferred
                )
            }
        }
    }
}