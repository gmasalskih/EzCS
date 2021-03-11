package ru.gmasalskikh.ezcs.screens.map_callouts_details

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
import ru.gmasalskikh.ezcs.utils.toValidId

class MapCalloutsDetailsViewModel(
    private val mapName: String,
    private val serviceProvider: ServiceProvider,
) : BaseViewModel<MapCalloutsDetailsViewState, Nothing>() {

    private val ceh: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            setSideEffect(SideEffect.Error(err = throwable, msgErr = throwable.message))
        }

    companion object {
        const val MAP_CALLOUTS_MAP_NAME: String = "MAP_CALLOUTS_MAP_NAME"
    }


    override val container: Container<MapCalloutsDetailsViewState, SideEffect> = container(
        initialState = MapCalloutsDetailsViewState(),
        onCreate = { initState() }
    )

    private fun setEntity(mapHolder: MapHolder) = intent {
        reduce {
            state.copy(mapCallouts = mapHolder)
        }
        setSideEffect(SideEffect.Data)
    }

    private fun initState() = viewModelScope.launch(ceh) {
        serviceProvider.getEntity(
            EntityType.MAP_HOLDER,
            mapName.toValidId(),
            MapHolderFirestoreEntity::class.java,
            serviceProvider.mapper.mapHolder
        ).let { mapHolder -> setEntity(mapHolder) }
    }
}