package ru.gmasalskikh.ezcs.screens.ranks.danger_zone

import androidx.compose.runtime.Composable
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.screens.ranks.danger_zone.widgets.DangerZoneContent

class DangerZoneView(
    vm: DangerZoneViewModel
) : BaseView<DangerZoneViewState, Nothing, DangerZoneViewModel>(vm) {

    @Composable
    override fun SetContent(viewState: DangerZoneViewState) {
        DangerZoneContent(text = viewState.name)
    }
}