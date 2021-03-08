package ru.gmasalskikh.ezcs.screens.ranks.competitive

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.screens.ranks.competitive.widgets.CompetitiveItem
import ru.gmasalskikh.ezcs.utils.LocalAppTheme

class CompetitiveView(
    vm: CompetitiveViewModel
) : BaseView<CompetitiveViewState, Nothing, CompetitiveViewModel>(vm) {

    @Composable
    override fun SetContent(viewState: CompetitiveViewState) {
        val theme = LocalAppTheme.current
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(viewState.items.sortedBy { it.order }) { competitive ->
                CompetitiveItem(
                    colorProgressIndicator = theme.colors.primary,
                    name = competitive.name,
                    contentDescription = competitive.logoDescription,
                    deferredBitmap = competitive.logoDeferred
                )
            }
        }
    }
}