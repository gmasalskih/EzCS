package ru.gmasalskikh.ezcs.screens.map_callouts

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.data.app_entity.MapHolder
import ru.gmasalskikh.ezcs.data.firestore_entities.MapHolderFirestoreEntity
import ru.gmasalskikh.ezcs.data.type.EntityType
import ru.gmasalskikh.ezcs.providers.service_provider.ServiceProvider
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect

class MapCalloutsViewModel(
    private val serviceProvider: ServiceProvider
) : BaseViewModel<MapCalloutsViewState, MapCalloutsViewEvent>() {

    private val ceh: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            setSideEffect(SideEffect.Error(err = throwable, msgErr = throwable.message))
        }

    override val container: Container<MapCalloutsViewState, SideEffect> = container(
        initialState = MapCalloutsViewState(),
        onCreate = { initState() }
    )

    override suspend fun onViewEvent(viewEvent: MapCalloutsViewEvent) {

    }

    private fun setList(maps: List<MapHolder>) = intent {
        reduce {
            state.copy(
                maps = maps
            )
        }
    }

    private fun initState() = viewModelScope.launch(ceh) {
        serviceProvider.getEntityList(
            entityType = EntityType.MAP_HOLDER,
            clazz = MapHolderFirestoreEntity::class.java,
            mapper = serviceProvider.mapper.mapHolder
        ).let { setList(it) }
        setSideEffect(SideEffect.Data)
    }
}