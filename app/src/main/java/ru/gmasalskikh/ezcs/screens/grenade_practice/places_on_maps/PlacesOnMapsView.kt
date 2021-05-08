package ru.gmasalskikh.ezcs.screens.grenade_practice.places_on_maps

import androidx.compose.runtime.Composable
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.screens.grenade_practice.places_on_maps.widgets.PlacesOmMapItemContent
import ru.gmasalskikh.ezcs.ui.common_widget.MenuContent
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
        MenuContent(
            isScrollableMenu = true,
            menuItemSurfaceColor = theme.colors.surface,
            menuItemElevation = theme.elevations.medium,
            menuItemShape = theme.shapes.medium,
            menuItemBorder = theme.borders.medium,
            onMenuItemClick = { mapPoint ->
                emit(PlacesOnMapsViewEvent.NavigateTo(mapPoint = mapPoint))
            },
            items = viewState.items.filter { mapPoint ->
                mapPoint.tickrateTypes.contains(viewState.currentTickrateType)
            }
        ) {
            PlacesOmMapItemContent(mapPoint = it)
        }
        
/*
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(viewState.items) { mapPoint ->
                TickRate64Item(
                    name = mapPoint.mapId,
                    deferredStart = mapPoint.previewStartDeferred,
                    deferredEnd = mapPoint.previewEndDeferred
                )
            }

        }
*/
    }

}