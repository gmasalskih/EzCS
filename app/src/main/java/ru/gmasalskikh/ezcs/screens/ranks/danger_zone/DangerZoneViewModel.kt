package ru.gmasalskikh.ezcs.screens.ranks.danger_zone

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.data.app_entity.DangerZone
import ru.gmasalskikh.ezcs.data.firestore_entities.DangerZoneFirestoreEntity
import ru.gmasalskikh.ezcs.data.type.EntityType
import ru.gmasalskikh.ezcs.providers.service_provider.ServiceProvider
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect

class DangerZoneViewModel(
    private val serviceProvider: ServiceProvider
) : BaseViewModel<DangerZoneViewState, Nothing>() {

    private val ceh: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            setSideEffect(SideEffect.Error(err = throwable, msgErr = throwable.message))
        }

    override val container: Container<DangerZoneViewState, SideEffect> = container(
        initialState = DangerZoneViewState(),
        onCreate = { initState() }
    )

    private fun setList(list: List<DangerZone>) = intent {
        reduce {
            state.copy(
                items = list,
            )
        }
    }

    private fun initState() = viewModelScope.launch(ceh) {
        serviceProvider.getEntityList(
            EntityType.DANGER_ZONE,
            DangerZoneFirestoreEntity::class.java,
            serviceProvider.mapper.dangerZone
        ).let { setList(it) }
        setSideEffect(SideEffect.Data)
    }
}