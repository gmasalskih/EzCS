package ru.gmasalskikh.ezcs.screens.grenade_practice.places_on_maps

import androidx.compose.runtime.Composable
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.screens.grenade_practice.places_on_maps.widgets.PlacesOmMapItemContent
import ru.gmasalskikh.ezcs.ui.common_widget.ListContent
import ru.gmasalskikh.ezcs.utils.LocalAppTheme

class PlacesOnMapsView(
    vm: PlacesOnMapsViewModel
) : BaseView<
        PlacesOnMapsViewState,
        PlacesOnMapsViewEvent,
        PlacesOnMapsViewModel>(vm) {

    @Composable
    override fun SetContent(viewState: PlacesOnMapsViewState) {
        val theme = LocalAppTheme.current
        ListContent(
            isScrollableList = true,
            listItemSurfaceColor = theme.colors.surface,
            listItemElevation = theme.elevations.medium,
            listItemShape = theme.shapes.medium,
            listItemBorder = theme.borders.medium,
            onListItemClick = { mapPoint ->
                emit(PlacesOnMapsViewEvent.NavigateTo(mapPoint = mapPoint))
            },
            items = viewState.items.filter { mapPoint ->
                mapPoint.tickrateTypes.contains(viewState.currentTickrateType)
            }
        ) {
            PlacesOmMapItemContent(mapPoint = it)
        }
    }
}