package ru.gmasalskikh.ezcs.screens.ranks.profile_rank

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.data.app_entity.ProfileRank
import ru.gmasalskikh.ezcs.data.firestore_entities.ProfileRankFirestoreEntity
import ru.gmasalskikh.ezcs.data.type.EntityType
import ru.gmasalskikh.ezcs.providers.service_provider.ServiceProvider
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect

class ProfileRankViewModel(
    private val serviceProvider: ServiceProvider
) : BaseViewModel<ProfileRankViewState, Nothing>() {

    override val container: Container<ProfileRankViewState, SideEffect> = container(
        initialState = ProfileRankViewState(),
        onCreate = { initState() }
    )

    private fun setList(list: List<ProfileRank>) = intent {
        reduce {
            state.copy(
                items = list,
            )
        }
    }

    private fun initState() = viewModelScope.launch {
        try {
            serviceProvider.getEntityList(
                EntityType.PROFILE_RANK,
                ProfileRankFirestoreEntity::class.java,
                serviceProvider.mapper.profileRank
            ).let { setList(it) }
            setSideEffect(SideEffect.Data)
        } catch (e: Throwable) {
            setSideEffect(SideEffect.Error(err = e, msgErr = e.message))
        }
    }
}