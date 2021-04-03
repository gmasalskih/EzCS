package ru.gmasalskikh.ezcs.screens.grenade_practice_type_of_grenade

import android.util.Log
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.data.app_entity.MapHolder
import ru.gmasalskikh.ezcs.data.firestore_entities.MapHolderFirestoreEntity
import ru.gmasalskikh.ezcs.data.firestore_entities.MapPointFirestoreEntity
import ru.gmasalskikh.ezcs.data.type.EntityType
import ru.gmasalskikh.ezcs.data.type.GrenadeType
import ru.gmasalskikh.ezcs.navigation.TargetNavigationPath
import ru.gmasalskikh.ezcs.providers.app_controller.AppController
import ru.gmasalskikh.ezcs.providers.custom_coroutine_scope.CustomCoroutineScope
import ru.gmasalskikh.ezcs.providers.scope_manager.ScopeClosable
import ru.gmasalskikh.ezcs.providers.service_provider.ServiceProvider
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.utils.toValidId

class GrenadePracticeTypeOfGrenadeViewModel(
    private val serviceProvider: ServiceProvider,
    private val appEventCollector: SharedFlow<AppController.AppEvent>,
    private val cs: CustomCoroutineScope
) :
    BaseViewModel<GrenadePracticeTypeOfGrenadeViewState, GrenadePracticeTypeOfGrenadeViewEvent>(),
    ScopeClosable {

    override val container: Container<GrenadePracticeTypeOfGrenadeViewState, SideEffect> =
        container(
            initialState = GrenadePracticeTypeOfGrenadeViewState(),
            onCreate = {
                cs.onStart()
                initState()
                subscribeToAppEvent()
            }
        )

    private fun initState() = cs.launch {
        serviceProvider.getEntityList(
            entityType = EntityType.MAP_POINT,
            clazz = MapPointFirestoreEntity::class.java,
            mapper = serviceProvider.mapper.mapPoint
        ).flatMap { mapPoint ->
            serviceProvider.getEntityList(
                entityType = EntityType.MAP_HOLDER,
                clazz = MapHolderFirestoreEntity::class.java,
                mapper = serviceProvider.mapper.mapHolder
            ).filter { mapHolder ->
                mapPoint.mapId == mapHolder.name.toValidId()
            }
        }.apply {
            setMapHolderList(this)
        }
        setSideEffect(SideEffect.Data)
    }

    private fun setCurrentGrenadeType(grenadeType: GrenadeType) = intent {
        reduce {
            state.copy(
                grenadeType = grenadeType,
            )
        }
    }

    private fun setMapHolderList(mapHolders: List<MapHolder>) = intent {
        reduce {
            state.copy(
                mapHolders = mapHolders
            )
        }
    }

    override suspend fun onViewEvent(viewEvent: GrenadePracticeTypeOfGrenadeViewEvent) {
    }

    private fun subscribeToAppEvent() = cs.launch {
        appEventCollector.collect {
            (it as? AppController.AppEvent.OnNavigateDestinationChanged)?.navEvent?.path?.let { path ->
                when (path) {
                    TargetNavigationPath.GRENADE_PRACTICE_SMOKE -> GrenadeType.SMOKE
                    TargetNavigationPath.GRENADE_PRACTICE_MOLOTOV -> GrenadeType.MOLOTOV
                    TargetNavigationPath.GRENADE_PRACTICE_FLASH -> GrenadeType.FLASH
                    else -> null
                }?.let { wt -> setCurrentGrenadeType(wt) }
            }
        }
    }

    override fun close() {
        cs.onStop()
        onCleared()
        Log.d("---", "GrenadesPracticeViewModel onCleared $this")
    }
}