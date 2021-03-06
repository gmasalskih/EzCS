package ru.gmasalskikh.ezcs.screens.ranks.competitive

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.data.firestore_entities.CompetitiveFirestoreEntity
import ru.gmasalskikh.ezcs.data.pojo.Competitive
import ru.gmasalskikh.ezcs.data.type.EntityType
import ru.gmasalskikh.ezcs.providers.content_repository.ContentRepository
import ru.gmasalskikh.ezcs.providers.data_repository.DataRepository
import ru.gmasalskikh.ezcs.providers.service_provider.ServiceProvider
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect

class CompetitiveViewModel(
    private val serviceProvider: ServiceProvider
) : BaseViewModel<CompetitiveViewState, Nothing>() {

    override val container: Container<CompetitiveViewState, SideEffect> = container(
        initialState = CompetitiveViewState(),
        onCreate = { initState() }
    )

    private fun setListOfCompetitive(listOfCompetitive: List<Competitive>) = intent {
        reduce {
            state.copy(
                listOfCompetitive = listOfCompetitive,
                currentSideEffect = SideEffect.Data
            )
        }
        postSideEffect(SideEffect.Data)
    }

    private fun initState() = viewModelScope.launch {
        setListOfCompetitive(serviceProvider.getCompetitiveList())
    }
}