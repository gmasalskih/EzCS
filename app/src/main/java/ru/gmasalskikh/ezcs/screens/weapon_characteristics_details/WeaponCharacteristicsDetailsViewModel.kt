package ru.gmasalskikh.ezcs.screens.weapon_characteristics_details

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.data.firestore_entities.WeaponFirestoreEntity
import ru.gmasalskikh.ezcs.data.type.EntityType
import ru.gmasalskikh.ezcs.data.view_entity.WeaponItem
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

    private fun setEntity(weaponItem: WeaponItem) = intent {
        reduce { state.copy(weaponItem = weaponItem) }
    }

    private fun initState() = viewModelScope.launch(ceh) {
        serviceProvider.getEntity(
            entityType = EntityType.WEAPON,
            entityName = weaponName.toValidId(),
            clazz = WeaponFirestoreEntity::class.java,
            mapper = serviceProvider.mapper.weaponItem
        ).let { weaponItem -> setEntity(weaponItem) }
        setSideEffect(SideEffect.Data)
    }
}