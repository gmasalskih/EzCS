package ru.gmasalskikh.ezcs.screens.weapon_characteristics

import android.util.Log
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect

class WeaponCharacteristicsViewModel :
    BaseViewModel<WeaponCharacteristicsViewState, WeaponCharacteristicsViewEvent>() {

    init {
        Log.d("---", "WeaponCharacteristicsViewModel init $this")
    }

    override val container: Container<WeaponCharacteristicsViewState, SideEffect> = container(
        initialState = WeaponCharacteristicsViewState()
    )


    override suspend fun onViewEvent(viewEvent: WeaponCharacteristicsViewEvent) {

    }

    public override fun onCleared() {
        super.onCleared()
        Log.d("---", "WeaponCharacteristicsViewModel onCleared $this")
    }
}