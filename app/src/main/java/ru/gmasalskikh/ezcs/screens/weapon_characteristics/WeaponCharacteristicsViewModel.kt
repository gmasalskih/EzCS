package ru.gmasalskikh.ezcs.screens.weapon_characteristics

import android.util.Log
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.data.app_entity.Weapon
import ru.gmasalskikh.ezcs.data.firestore_entities.WeaponFirestoreEntity
import ru.gmasalskikh.ezcs.data.type.EntityType
import ru.gmasalskikh.ezcs.data.type.WeaponType
import ru.gmasalskikh.ezcs.navigation.TargetNavigationPath
import ru.gmasalskikh.ezcs.providers.app_controller.AppController
import ru.gmasalskikh.ezcs.providers.custom_coroutine_scope.CustomCoroutineScope
import ru.gmasalskikh.ezcs.providers.scope_manager.ScopeClosable
import ru.gmasalskikh.ezcs.providers.service_provider.ServiceProvider
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect

class WeaponCharacteristicsViewModel(
    private val serviceProvider: ServiceProvider,
    private val appEventCollector: SharedFlow<AppController.AppEvent>,
    private val cs: CustomCoroutineScope
) :
    BaseViewModel<WeaponCharacteristicsViewState, WeaponCharacteristicsViewEvent>(), ScopeClosable {

    override val container: Container<WeaponCharacteristicsViewState, SideEffect> = container(
        initialState = WeaponCharacteristicsViewState(),
        onCreate = {
            cs.onStart()
            subscribeToAppEvent()
            initState()
        }
    )

    override suspend fun onViewEvent(viewEvent: WeaponCharacteristicsViewEvent) {
    }

    private fun subscribeToAppEvent() = cs.launch {
        appEventCollector.collect {
            (it as? AppController.AppEvent.OnNavigateDestinationChanged)?.navEvent?.path?.let { path ->
                when (path) {
                    TargetNavigationPath.WEAPON_CHARACTERISTICS_PISTOL -> WeaponType.PISTOL
                    TargetNavigationPath.WEAPON_CHARACTERISTICS_HEAVY -> WeaponType.HEAVY
                    TargetNavigationPath.WEAPON_CHARACTERISTICS_SMG -> WeaponType.SMG
                    TargetNavigationPath.WEAPON_CHARACTERISTICS_RIFLE -> WeaponType.RIFLE
                    else -> null
                }?.let { wt -> setCurrentWeaponType(wt) }
            }
        }
    }

    private fun setCurrentWeaponType(weaponType: WeaponType) = intent {
        reduce {
            state.copy(
                currentWeaponType = weaponType
            )
        }
    }

    private fun setListOfWeapon(weapons: List<Weapon>) = intent {
        reduce {
            state.copy(
                weapons = weapons
            )
        }
    }

    private fun initState() = cs.launch {
        serviceProvider.getEntityList(
            entityType = EntityType.WEAPON,
            clazz = WeaponFirestoreEntity::class.java,
            serviceProvider.mapper.weapon
        ).let { weapons -> setListOfWeapon(weapons) }
        setSideEffect(SideEffect.Data)
    }

    override fun close() {
        cs.onStop()
        onCleared()
        Log.d("---", "WeaponCharacteristicsViewModel onCleared $this")
    }
}