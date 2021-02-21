package ru.gmasalskikh.ezcs.screens.preview

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.screens.preview.widgets.*
import ru.gmasalskikh.ezcs.ui.theme.*
import ru.gmasalskikh.ezcs.utils.AmbientAppTheme

class PreviewView(
    vm: PreviewViewModel
) : BaseView<PreviewViewState, PreviewViewEvent, PreviewViewModel>(vm) {

    @Composable
    override fun SetContent(viewState: PreviewViewState) {
        val theme: AppTheme = AmbientAppTheme.current
        val currentIndexPage: Int = viewState.pagerState?.currentPage ?: 0

        @StringRes
        val currentTopic: Int = viewState.items[currentIndexPage].topicRes

        PreviewContent(
            currentTopic = stringResource(id = currentTopic),
            mainColor = theme.colors.onBackground,
            accentColor = theme.colors.primary,
            backgroundColor = theme.colors.surface,
            border = theme.borders.medium,
            shape = theme.shapes.medium,
            setPagerState = { pagerState ->
                emit(PreviewViewEvent.SetPagerState(pagerState))
            },
            pagerState = viewState.pagerState ?: getPagerState(),
            items = viewState.items,
            currentIndexPage = currentIndexPage,
            navigateToMainMenu = {
                emit(PreviewViewEvent.NavigateNext)
            },
            skipText = stringResource(id = R.string.skip)
        )
    }
}


