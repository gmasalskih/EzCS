package ru.gmasalskikh.ezcs.screens.map_callouts

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AmbientViewModelStoreOwner
import ru.gmasalskikh.ezcs.screens.BaseView

class MapCalloutsView(
    vm: MapCalloutsViewModel
) : BaseView<MapCalloutsViewState, MapCalloutsViewEvent, MapCalloutsViewModel>(vm) {

    @Composable
    override fun SetContent(viewState: MapCalloutsViewState) {
        AmbientViewModelStoreOwner.current.viewModelStore
        Text(text = viewState.name)
    }
}