package ru.gmasalskikh.ezcs.screens.preview

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.screens.preview.widgets.*
import ru.gmasalskikh.ezcs.ui.theme.*
import ru.gmasalskikh.ezcs.utils.AmbientAppTheme
import ru.gmasalskikh.ezcs.utils.AmbientNavController
import java.util.*

class PreviewView(
    override val vm: PreviewViewModel
) : BaseView<PreviewViewModel>() {

    @Composable
    override fun SetContent() {
        val theme: AppTheme = AmbientAppTheme.current
        val navController = AmbientNavController.current
        PreviewContent(
            currentTopic = stringResource(id = vm.getCurrentTopic()),
            mainColor = theme.colors.onBackground,
            accentColor = theme.colors.primary,
            backgroundColor = theme.colors.surface,
            border = theme.borders.medium,
            shape = theme.shapes.medium,
            setPagerState = vm::setPagerState,
            pagerState = vm.getPagerState() ?: getPagerState(),
            items = vm.viewState.items,
            currentIndexPage = vm.getCurrentIndexPage(),
            navigateToMainMenu = { vm.navigateToMainMenu(navController) },
            skipText = stringResource(id = R.string.skip)
        )
    }
}

@Composable
private fun PreviewContent(
    currentTopic: String,
    mainColor: Color,
    accentColor: Color,
    backgroundColor: Color,
    border: BorderStroke,
    shape: CornerBasedShape,
    setPagerState: (PagerState) -> Unit,
    pagerState: PagerState,
    items: List<PreviewViewState.PreviewItem>,
    currentIndexPage: Int,
    navigateToMainMenu: () -> Unit,
    skipText: String,
) {
    Column {
        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = currentTopic.toUpperCase(Locale.getDefault()),
                color = mainColor
            )
        }
        ViewPager(
            modifier = Modifier.weight(1f),
            items = items,
            pagerState = pagerState
        ) { scope, item ->
            setPagerState(scope.currentPagerState)
            PreviewItem(
                border = border,
                shape = shape,
                backgroundColor = backgroundColor
            ) {
                CoilImage(
                    modifier = Modifier.padding(vertical = 10.dp),
                    data = item.imageRes,
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
                amountDots = items.size,
                color = mainColor, accentColor = accentColor,
                indexSelectedDot = currentIndexPage
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable(onClick = navigateToMainMenu)
                    .padding(end = 20.dp),
                text = "${skipText.toUpperCase(Locale.getDefault())} >",
                color = accentColor,
                fontSize = fontSize12Sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
