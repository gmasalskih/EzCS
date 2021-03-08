package ru.gmasalskikh.ezcs.screens.ranks.competitive

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.data.app_entity.Competitive
import ru.gmasalskikh.ezcs.data.firestore_entities.CompetitiveFirestoreEntity
import ru.gmasalskikh.ezcs.data.type.EntityType
import ru.gmasalskikh.ezcs.providers.service_provider.ServiceProvider
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect

class CompetitiveViewModel(
    private val serviceProvider: ServiceProvider,
) : BaseViewModel<CompetitiveViewState, Nothing>() {

    override val container: Container<CompetitiveViewState, SideEffect> = container(
        initialState = CompetitiveViewState(),
        onCreate = { initState() }
    )

    private fun setList(listOfCompetitive: List<Competitive>) = intent {
        reduce {
            state.copy(
                items = listOfCompetitive,
            )
        }
    }

    private fun initState() = viewModelScope.launch {
        try {
            serviceProvider.getEntityList(
                EntityType.COMPETITIVE,
                CompetitiveFirestoreEntity::class.java,
                serviceProvider.mapper.competitive
            ).let { setList(it) }
            setSideEffect(SideEffect.Data)
        } catch (e: Throwable) {
            setSideEffect(SideEffect.Error(err = e, msgErr = e.message))
        }
    }
}