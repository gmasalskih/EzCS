package ru.gmasalskikh.ezcs.screens.grenade_practice.grenade_practice_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.screens.grenade_practice.grenade_practice_details.widgets.GrenadePracticeDetailsImageItem
import ru.gmasalskikh.ezcs.screens.grenade_practice.grenade_practice_details.widgets.GrenadePracticeDetailsVideoItem

class GrenadePracticeDetailsView(
    vm: GrenadePracticeDetailsViewModel
) : BaseView<GrenadePracticeDetailsViewState, Nothing, GrenadePracticeDetailsViewModel>(vm) {

    @Composable
    override fun SetContent(viewState: GrenadePracticeDetailsViewState) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(20.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            viewState.mapPoint?.contentVideos?.forEach {
//                GrenadePracticeDetailsVideoItem(it)
//            }
            viewState.mapPoint?.contentImages?.forEach {
                GrenadePracticeDetailsImageItem(it)
            }
        }
    }
}