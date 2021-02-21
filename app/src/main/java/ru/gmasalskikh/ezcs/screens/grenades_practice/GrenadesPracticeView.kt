package ru.gmasalskikh.ezcs.screens.grenades_practice

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import ru.gmasalskikh.ezcs.screens.BaseView

class GrenadesPracticeView(
    vm: GrenadesPracticeViewModel
) : BaseView<GrenadesPracticeViewState, GrenadesPracticeViewEvent, GrenadesPracticeViewModel>(vm) {

    @Composable
    override fun SetContent(viewState: GrenadesPracticeViewState) {
        Text(text = viewState.name)
    }
}