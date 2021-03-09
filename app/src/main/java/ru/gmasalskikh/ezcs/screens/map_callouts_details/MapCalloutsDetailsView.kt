package ru.gmasalskikh.ezcs.screens.map_callouts_details

import androidx.compose.runtime.Composable
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.screens.map_callouts_details.widgets.MapCalloutsDetailsContent

class MapCalloutsDetailsView(
    vm: MapCalloutsDetailsViewModel
) : BaseView<MapCalloutsDetailsViewState, Nothing, MapCalloutsDetailsViewModel>(vm) {

    @Composable
    override fun SetContent(viewState: MapCalloutsDetailsViewState) {
        viewState.mapCallouts?.let { mapHolder ->
            MapCalloutsDetailsContent(mapHolder)
        }
    }
}