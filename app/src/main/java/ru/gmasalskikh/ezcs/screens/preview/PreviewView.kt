package ru.gmasalskikh.ezcs.screens.preview

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.chrisbanes.accompanist.coil.CoilImage
import org.koin.androidx.compose.getViewModel
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.data.model.preview.PreviewItemType
import ru.gmasalskikh.ezcs.screens.preview.widgets.*
import ru.gmasalskikh.ezcs.ui.theme.*
import ru.gmasalskikh.ezcs.utils.AmbientAppTheme
import ru.gmasalskikh.ezcs.utils.AmbientNavController
import java.util.*

@DrawableRes
private fun getPreviewItemDrawableRes(itemType: PreviewItemType): Int = when (itemType) {
    PreviewItemType.MAP_CALLOUTS -> R.drawable.preview_map_callouts
    PreviewItemType.COMPARE_WEAPONS -> R.drawable.preview_compare_weapons
    PreviewItemType.GRENADES_PRACTICE -> R.drawable.preview_grenades_practice
    PreviewItemType.WEAPON_CHARACTERISTICS -> R.drawable.preview_weapon_characteristics
    PreviewItemType.RANKS -> R.drawable.preview_rangs
}

@Composable
fun PreviewView(
    navController: NavController = AmbientNavController.current,
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
                text = stringResource(id = vm.getCurrentTopicRes()).toUpperCase(Locale.getDefault()),
                color = theme.colors.onBackground
            )
        }
        ViewPager(
            modifier = Modifier.weight(1f),
            items = vm.viewState.items,
            pagerState = vm.getPagerState() ?: getPagerState()
        ) { scope, item ->
            vm.setPagerState(scope.currentPagerState)
            vm.getCurrentTopicRes()
            PreviewItem(
                border = theme.borders.medium,
                shape = theme.shapes.medium,
                backgroundColor = theme.colors.surface
            ) {
                CoilImage(
                    modifier = Modifier.padding(vertical = 10.dp),
                    data = getPreviewItemDrawableRes(item.type),
                    contentDescription = null,
                    loading = {
                        CircularProgressIndicator()
                    }
                )
            }
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
                text = "${stringResource(R.string.skip).toUpperCase(Locale.getDefault())} >",
                color = theme.colors.primary,
                fontSize = theme.typography.body2.fontSize
            )
        }
    }
}
