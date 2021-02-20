package ru.gmasalskikh.ezcs.screens.splash_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.screens.splash_screen.widgets.SplashScreenContent
import ru.gmasalskikh.ezcs.utils.AmbientAppTheme
import ru.gmasalskikh.ezcs.utils.DELAY_SPLASH_SCREEN
import ru.gmasalskikh.ezcs.utils.bitmapFromResources

class SplashScreenView(
    override val vm: SplashScreenViewModel,
) : BaseView<SplashScreenViewModel>() {
    
    @Composable
    override fun SetContent() {
        val viewState =
            vm.container.stateFlow.collectAsState(initial = vm.container.currentState).value
        val theme = AmbientAppTheme.current
        rememberCoroutineScope().launch {
            delay(DELAY_SPLASH_SCREEN)
            vm.navigate()
        }
        SplashScreenContent(
            appDescription = stringResource(id = viewState.appDescriptionRes),
            appLogo = bitmapFromResources(id = viewState.appLogoRes),
            appDescriptionColor = theme.colors.onBackground,
        )
    }
}