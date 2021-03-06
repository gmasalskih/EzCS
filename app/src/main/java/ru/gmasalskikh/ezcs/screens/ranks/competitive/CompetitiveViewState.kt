package ru.gmasalskikh.ezcs.screens.ranks.competitive

import ru.gmasalskikh.ezcs.data.firestore_entities.CompetitiveFirestoreEntity
import ru.gmasalskikh.ezcs.data.pojo.Competitive
import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.ViewState

data class CompetitiveViewState(
    val listOfCompetitive: List<Competitive> = listOf(),
    override val currentSideEffect: SideEffect = SideEffect.Loading
) : ViewState