package ru.gmasalskikh.ezcs.screens.map_callouts

import androidx.compose.runtime.Composable
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.screens.map_callouts.widgets.MapCalloutsItemContent
import ru.gmasalskikh.ezcs.ui.common_widget.ListContent
import ru.gmasalskikh.ezcs.utils.LocalAppTheme

class MapCalloutsView(
    vm: MapCalloutsViewModel
) : BaseView<MapCalloutsViewState, MapCalloutsViewEvent, MapCalloutsViewModel>(vm) {

    @Composable
    override fun SetContent(viewState: MapCalloutsViewState) {
        val theme = LocalAppTheme.current
        ListContent(
            isScrollableList = true,
            listItemSurfaceColor = theme.colors.surface,
            listItemElevation = theme.elevations.medium,
            listItemShape = theme.shapes.medium,
            listItemBorder = theme.borders.medium,
            onListItemClick = { mapHolder ->
                emit(MapCalloutsViewEvent.NavigateTo(mapHolder = mapHolder))
            },
            items = viewState.maps
        ) { mapHolder ->
            MapCalloutsItemContent(mapHolder = mapHolder)
        }
    }
}