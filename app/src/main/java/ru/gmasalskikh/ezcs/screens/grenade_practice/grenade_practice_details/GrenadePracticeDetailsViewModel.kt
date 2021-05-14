package ru.gmasalskikh.ezcs.screens.grenade_practice.grenade_practice_details

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.data.app_entity.MapPoint
import ru.gmasalskikh.ezcs.data.firestore_entities.MapPointFirestoreEntity
import ru.gmasalskikh.ezcs.data.type.EntityType
import ru.gmasalskikh.ezcs.providers.app_controller.AppController
import ru.gmasalskikh.ezcs.providers.service_provider.ServiceProvider
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.utils.toValidId

class GrenadePracticeDetailsViewModel(
    private val fairName: String,
    private val serviceProvider: ServiceProvider,
)
    : BaseViewModel<GrenadePracticeDetailsViewState, Nothing>() {

    private val ceh: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            setSideEffect(SideEffect.Error(err = throwable, msgErr = throwable.message))
        }

    companion object {
        const val GRENADE_PRACTICE_DETAILS_MAPPOINT_NAME = "GRENADE_PRACTICE_MAPPOINT_NAME"
        const val GRENADE_PRACTICE_DETAILS_MAPPOINT_FAIR_NAME = "GRENADE_PRACTICE_MAPPOINT_FAIR_NAME"
    }

    override val container: Container<GrenadePracticeDetailsViewState, SideEffect> = container(
        initialState = GrenadePracticeDetailsViewState(SideEffect.Loading, null),
        onCreate = {
            initState()
        }
    )

    private fun initState() = viewModelScope.launch(ceh) {
        serviceProvider.getEntity(
            entityType = EntityType.MAP_POINT,
            entityName = fairName.toValidId(),
            clazz = MapPointFirestoreEntity::class.java,
            mapper = serviceProvider.mapper.mapPoint
        ).let { mapPoint ->
            setEntity(mapPoint)
        }
        setSideEffect(SideEffect.Data)
    }

    private fun setEntity(mapPoint: MapPoint) = intent {
        reduce {
            state.copy(mapPoint = mapPoint)
        }
    }

}