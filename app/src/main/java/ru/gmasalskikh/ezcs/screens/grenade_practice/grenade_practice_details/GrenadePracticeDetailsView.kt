package ru.gmasalskikh.ezcs.screens.grenade_practice.grenade_practice_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.screens.grenade_practice.grenade_practice_details.widgets.GrenadePracticeDetailsImageItem
import ru.gmasalskikh.ezcs.screens.grenade_practice.grenade_practice_details.widgets.GrenadePracticeDetailsVideoItem
import ru.gmasalskikh.ezcs.utils.LocalAppTheme

class GrenadePracticeDetailsView(
    vm: GrenadePracticeDetailsViewModel
) : BaseView<GrenadePracticeDetailsViewState, Nothing, GrenadePracticeDetailsViewModel>(vm) {

    @Composable
    override fun SetContent(viewState: GrenadePracticeDetailsViewState) {
        val theme = LocalAppTheme.current
        LazyColumn(
            modifier = Modifier
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            viewState.mapPoint?.let {
                items(it.contentImages) { image ->
                    GrenadePracticeDetailsImageItem(image)
                }
            }
        }
/*
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
//            verticalArrangement = Arrangement.spacedBy(20.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Column for video
            LazyColumn(
                modifier = Modifier
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                viewState.mapPoint?.let {
                    items(it.contentVideos) { video ->
                        GrenadePracticeDetailsVideoItem(video)
                    }
                }
            }
            // Column for image
            LazyColumn(
                modifier = Modifier
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                viewState.mapPoint?.let {
                    items(it.contentImages) { image ->
                        GrenadePracticeDetailsImageItem(image)
                    }
                }
            }
        }
*/
    }
}