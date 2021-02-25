package ru.gmasalskikh.ezcs.screens.weapon_characteristics

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.navigation.Navigator
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.navigation.TargetNavigationPath
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect

class WeaponCharacteristicsViewModel(
    private val navEventCollector: Flow<Navigator.NavEvent>,
    private val navEventEmitter: FlowCollector<TargetNavigation>
) : BaseViewModel<WeaponCharacteristicsViewState, WeaponCharacteristicsViewEvent>() {

    init {
        Log.d("---", "WeaponCharacteristicsViewModel init $this")
    }

    override val container: Container<WeaponCharacteristicsViewState, SideEffect> = container(
        initialState = WeaponCharacteristicsViewState(),
        onCreate = {
            viewModelScope.launch {
                navEventCollector.collect {
                    aaa(it.path.name)
                }
            }
        }
    )


    override suspend fun onViewEvent(viewEvent: WeaponCharacteristicsViewEvent) {

    }

    private fun aaa(s:String) = intent {
        reduce {
            state.copy(
                name = s
            )
        }
    }

    fun destroy(){
        Log.d("---", "WeaponCharacteristicsViewModel destroy $this")
    }

    override fun onViewCreate() {
        super.onViewCreate()

    }

    override fun onCleared() {
        super.onCleared()
        Log.d("---", "WeaponCharacteristicsViewModel onCleared $this")
    }
}