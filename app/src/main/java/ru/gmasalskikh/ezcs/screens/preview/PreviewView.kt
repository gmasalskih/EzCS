package ru.gmasalskikh.ezcs.screens.preview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.compose.getViewModel
import ru.gmasalskikh.ezcs.screens.preview.widgets.Dots
import ru.gmasalskikh.ezcs.screens.preview.widgets.PreviewItem
import ru.gmasalskikh.ezcs.screens.preview.widgets.ViewPager
import ru.gmasalskikh.ezcs.screens.preview.widgets.getPagerState
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.ui.theme.*
import ru.gmasalskikh.ezcs.utils.AmbientAppTheme
import ru.gmasalskikh.ezcs.utils.AmbientNavController


@Composable
fun PreviewView(
    navController: NavController = AmbientNavController.current,
    cs: CoroutineScope = rememberCoroutineScope(),
    theme: AppTheme = AmbientAppTheme.current,
    vm: PreviewViewModel = getViewModel()
) {
    Column {
        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = vm.getCurrentItem().toUpperCase(),
                color = theme.colors.onBackground
            )
        }
        ViewPager(
            modifier = Modifier.weight(1f),
            items = vm.viewState.items,
            pagerState = vm.getPagerState() ?: getPagerState()
        ) { scope, item ->
            vm.setPagerState(scope.currentPagerState)
            PreviewItem(
                border = theme.borders.medium,
                shape = theme.shapes.medium,
                backgroundColor = theme.colors.surface
            )
        }
        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
        ) {
            Dots(
                modifier = Modifier.align(Alignment.Center),
                size = 8.dp,
                amountDots = vm.viewState.items.size,
                color = theme.colors.onBackground, accentColor = theme.colors.primary,
                indexSelectedDot = vm.getCurrentIndexPage()
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable(onClick = { vm.navigateToMainMenu(navController) })
                    .padding(end = 20.dp),
                text = "${stringResource(R.string.skip).toUpperCase()} >",
                color = theme.colors.primary,
                fontSize = theme.typography.body2.fontSize
            )
        }
    }
}
