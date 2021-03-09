package ru.gmasalskikh.ezcs.screens.ranks.profile_rank

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.screens.ranks.profile_rank.widgets.ProfileRankItem
import ru.gmasalskikh.ezcs.utils.LocalAppTheme

class ProfileRankView(
    vm: ProfileRankViewModel
) : BaseView<ProfileRankViewState, Nothing, ProfileRankViewModel>(vm) {

    @Composable
    override fun SetContent(viewState: ProfileRankViewState) {
        val theme = LocalAppTheme.current
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(viewState.items.sortedBy { it.order }) { profileRank ->
                ProfileRankItem(
                    colorProgressIndicator = theme.colors.primary,
                    name = profileRank.name,
                    xp = profileRank.xp,
                    contentDescription = profileRank.logoDescription,
                    deferredBitmap = profileRank.logoDeferred
                )
            }
        }
    }
}