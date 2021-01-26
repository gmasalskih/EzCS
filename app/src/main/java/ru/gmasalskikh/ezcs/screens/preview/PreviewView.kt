package ru.gmasalskikh.ezcs.screens.preview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import ru.gmasalskikh.ezcs.screens.preview.widgets.Dots
import ru.gmasalskikh.ezcs.screens.preview.widgets.PreviewItem
import ru.gmasalskikh.ezcs.screens.preview.widgets.ViewPager
import ru.gmasalskikh.ezcs.screens.preview.widgets.getPagerState
import ru.gmasalskikh.ezcs.ui.theme.orange
import ru.gmasalskikh.ezcs.ui.theme.white
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.ui.theme.*
import ru.gmasalskikh.ezcs.utils.AmbientNavController

@Composable
fun PreviewView(
    navController: NavController = AmbientNavController.current,
    vm: PreviewViewModel = getViewModel { parametersOf(navController) }
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
                color = white
            )
        }
        ViewPager(
            modifier = Modifier.weight(1f),
            items = vm.viewState.items,
            pagerState = vm.getPagerState() ?: getPagerState()
        ) { scope, item ->
            vm.setPagerState(scope.currentPagerState)
            PreviewItem(
                name = "item: $item"
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
                color = white, accentColor = orange,
                indexSelectedDot = vm.getCurrentIndexPage()
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable(onClick = vm::navigateToMainMenu)
                    .padding(end = 20.dp),
                text = "${stringResource(R.string.skip).toUpperCase()} >",
                color = orange,
                fontSize = fontSize14Sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview_PreviewView() {
    PreviewView()
}