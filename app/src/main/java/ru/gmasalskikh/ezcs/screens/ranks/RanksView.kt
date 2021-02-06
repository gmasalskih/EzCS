package ru.gmasalskikh.ezcs.screens.ranks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.providers.mapper.ResourceMapper
import ru.gmasalskikh.ezcs.screens.ranks.widget.RanksBottomAppBar
import ru.gmasalskikh.ezcs.ui.common_widget.TopAppBar
import ru.gmasalskikh.ezcs.ui.theme.AppTheme
import ru.gmasalskikh.ezcs.utils.AmbientAppTheme
import ru.gmasalskikh.ezcs.utils.AmbientNavController

@Composable
fun Ranks(
    theme: AppTheme = AmbientAppTheme.current,
    navController: NavController = AmbientNavController.current,
    cs: CoroutineScope = rememberCoroutineScope(),
    vm: RanksViewModel = getViewModel(),
    resourceMapper: ResourceMapper = get()
) {
    TopAppBar(
        title = stringResource(R.string.preview_topic_ranks),
        backgroundColor = theme.colors.primary,
        contentColor = theme.colors.onPrimary,
        elevation = theme.elevations.medium,
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = vm.viewState.selectedRank.name,
                    textAlign = TextAlign.Center
                )
            }
            RanksBottomAppBar(
                modifier = Modifier.fillMaxWidth(),
                onBottomBarItemClick = { selectedRank ->
                    if (vm.viewState.selectedRank != selectedRank) vm.setSelectedRank(selectedRank)
                },
                itemsBackgroundColor = theme.colors.primary,
                list = vm.viewState.listRankBottomAppBarItem,
                currentSelectedItem = vm.viewState.selectedRank,
                activeItemColor = theme.colors.onPrimary,
                passiveItemColor = theme.colors.background.copy(alpha = 0.4f),
                resourceMapper = resourceMapper
            )
        }
    }
}