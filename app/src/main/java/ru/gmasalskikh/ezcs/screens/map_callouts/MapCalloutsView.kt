package ru.gmasalskikh.ezcs.screens.map_callouts

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import ru.gmasalskikh.ezcs.screens.BaseView

class MapCalloutsView(
    vm: MapCalloutsViewModel
) : BaseView<MapCalloutsViewState, MapCalloutsViewEvent, MapCalloutsViewModel>(vm) {

    @Composable
    override fun SetContent(viewState: MapCalloutsViewState) {
        Text(text = viewState.name)
    }
}