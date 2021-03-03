package ru.gmasalskikh.ezcs.screens.splash_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.screens.splash_screen.widgets.SplashScreenContent
import ru.gmasalskikh.ezcs.utils.LocalAppTheme
import ru.gmasalskikh.ezcs.utils.DELAY_SPLASH_SCREEN
import ru.gmasalskikh.ezcs.utils.bitmapFromResources

class SplashScreenView(vm: SplashScreenViewModel) :
    BaseView<SplashScreenViewState, SplashScreenViewEvent, SplashScreenViewModel>(vm) {

    @Composable
    override fun SetContent(viewState: SplashScreenViewState) {
        val theme = LocalAppTheme.current
        rememberCoroutineScope().launch {
            delay(DELAY_SPLASH_SCREEN)
            emit(SplashScreenViewEvent.NavigateNext)
        }

        SplashScreenContent(
            appDescription = stringResource(id = viewState.appDescriptionRes),
            appLogo = bitmapFromResources(id = viewState.appLogoRes),
            appDescriptionColor = theme.colors.onBackground,
        )
    }
}