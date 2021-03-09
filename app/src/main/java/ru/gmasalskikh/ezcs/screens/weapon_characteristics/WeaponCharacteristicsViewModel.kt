package ru.gmasalskikh.ezcs.screens.weapon_characteristics

import android.util.Log
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.providers.scope_manager.ScopeClosable
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect

class WeaponCharacteristicsViewModel :
    BaseViewModel<WeaponCharacteristicsViewState, WeaponCharacteristicsViewEvent>(), ScopeClosable {

    init {
        Log.d("---", "WeaponCharacteristicsViewModel init $this")
    }

    override val container: Container<WeaponCharacteristicsViewState, SideEffect> = container(
        initialState = WeaponCharacteristicsViewState()
    )


    override suspend fun onViewEvent(viewEvent: WeaponCharacteristicsViewEvent) {

    }

    override fun close() {
        onCleared()
        Log.d("---", "WeaponCharacteristicsViewModel onCleared $this")
    }
}