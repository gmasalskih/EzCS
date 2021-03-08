package ru.gmasalskikh.ezcs.screens.ranks.wingman

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.data.app_entity.Wingman
import ru.gmasalskikh.ezcs.data.firestore_entities.DangerZoneFirestoreEntity
import ru.gmasalskikh.ezcs.data.firestore_entities.WingmanFirestoreEntity
import ru.gmasalskikh.ezcs.data.type.EntityType
import ru.gmasalskikh.ezcs.providers.service_provider.ServiceProvider
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect

class WingmanViewModel(
    private val serviceProvider: ServiceProvider
) : BaseViewModel<WingmanViewState, Nothing>() {

    override val container: Container<WingmanViewState, SideEffect> = container(
        initialState = WingmanViewState(),
        onCreate = { initState() }
    )

    private fun setList(list: List<Wingman>) = intent {
        reduce {
            state.copy(
                items = list,
            )
        }
    }

    private fun initState() = viewModelScope.launch {
        try {
            serviceProvider.getEntityList(
                EntityType.WINGMAN,
                WingmanFirestoreEntity::class.java,
                serviceProvider.mapper.wingman
            ).let { setList(it) }
            setSideEffect(SideEffect.Data)
        } catch (e: Throwable) {
            setSideEffect(SideEffect.Error(err = e, msgErr = e.message))
        }
    }
}