package ru.gmasalskikh.ezcs.screens.weapon_characteristics_details

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.data.app_entity.Weapon
import ru.gmasalskikh.ezcs.data.firestore_entities.WeaponFirestoreEntity
import ru.gmasalskikh.ezcs.data.type.EntityType
import ru.gmasalskikh.ezcs.providers.service_provider.ServiceProvider
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.utils.toValidId

class WeaponCharacteristicsDetailsViewModel(
    private val weaponName: String,
    private val serviceProvider: ServiceProvider
) : BaseViewModel<WeaponCharacteristicsDetailsViewState, Nothing>() {

    private val ceh: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            setSideEffect(SideEffect.Error(err = throwable, msgErr = throwable.message))
        }

    companion object {
        const val WEAPON_ID: String = "WEAPON_ID"
        const val WEAPON_NAME: String = "WEAPON_NAME"
    }

    override val container: Container<WeaponCharacteristicsDetailsViewState, SideEffect> =
        container(
            initialState = WeaponCharacteristicsDetailsViewState(),
            onCreate = { initState() }
        )

    private fun setEntity(weapon: Weapon) = intent {
        reduce { state.copy(weapon = weapon) }
    }

    private fun initState() = viewModelScope.launch(ceh) {
        serviceProvider.getEntity(
            entityType = EntityType.WEAPON,
            entityName = weaponName.toValidId(),
            clazz = WeaponFirestoreEntity::class.java,
            mapper = serviceProvider.mapper.weapon
        ).let { weapon -> setEntity(weapon) }
        setSideEffect(SideEffect.Data)
    }
}