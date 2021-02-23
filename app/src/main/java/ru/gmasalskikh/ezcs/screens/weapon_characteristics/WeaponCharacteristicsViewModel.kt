package ru.gmasalskikh.ezcs.screens.weapon_characteristics

import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.weapon_characteristics.WeaponCharacteristicsViewEvent.*

class WeaponCharacteristicsViewModel :
    BaseViewModel<WeaponCharacteristicsViewState, WeaponCharacteristicsViewEvent>() {

    override val container: Container<WeaponCharacteristicsViewState, SideEffect> = container(
        initialState = WeaponCharacteristicsViewState()
    )

    override suspend fun onViewEvent(viewEvent: WeaponCharacteristicsViewEvent) {
        when (viewEvent) {
            is OnBottomBarItemNavigate -> {
                when (viewEvent.targetNavigation) {
                    //TODO
                }
            }
        }
    }

    private fun pistolSelected() = intent {
        reduce {
            state.copy(name = "PISTOL")
        }
    }

    private fun heavySelected() = intent {
        reduce {
            state.copy(name = "HEAVY")
        }
    }

    private fun smgSelected() = intent {
        reduce {
            state.copy(name = "SMG")
        }
    }

    private fun rifleSelected() = intent {
        reduce {
            state.copy(name = "RIFLE")
        }
    }
}