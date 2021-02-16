package ru.gmasalskikh.ezcs.screens.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.screens.preview.widgets.*
import ru.gmasalskikh.ezcs.ui.theme.*
import ru.gmasalskikh.ezcs.utils.AmbientAppTheme

class PreviewView(
    override val vm: PreviewViewModel
) : BaseView<PreviewViewModel>() {

    @Composable
    override fun SetContent() {
        val theme: AppTheme = AmbientAppTheme.current
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
            navigateToMainMenu = vm::navigateToMainMenu,
            skipText = stringResource(id = R.string.skip)
        )
    }
}


