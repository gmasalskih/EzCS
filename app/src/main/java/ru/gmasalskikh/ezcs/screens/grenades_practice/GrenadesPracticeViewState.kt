package ru.gmasalskikh.ezcs.screens.grenades_practice

import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.ViewState

data class GrenadesPracticeViewState(
    val name: String = "Grenades practice",
    override val currentSideEffect: SideEffect = SideEffect.Data
) : ViewState