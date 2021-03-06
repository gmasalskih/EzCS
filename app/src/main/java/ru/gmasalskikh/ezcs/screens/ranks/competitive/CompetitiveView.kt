package ru.gmasalskikh.ezcs.screens.ranks.competitive

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.ui.common_widget.ImageLoader
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
            items(viewState.listOfCompetitive.sortedBy { it.order }) { competitive ->
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ImageLoader(
                        modifier = Modifier.size(width = 100.dp, height = 40.dp),
                        colorProgressIndicator = theme.colors.primary,
                        contentDescription = competitive.logoName,
                        deferredBitmap = competitive.logoDeferred
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(20.dp)
                    )
                    Text(text = competitive.name)
                }
            }
        }
    }
}