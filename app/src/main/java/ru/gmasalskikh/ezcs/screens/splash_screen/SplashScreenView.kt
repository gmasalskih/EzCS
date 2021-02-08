package ru.gmasalskikh.ezcs.screens.splash_screen

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.*
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.utils.AmbientAppTheme
import ru.gmasalskikh.ezcs.utils.AmbientNavController
import ru.gmasalskikh.ezcs.utils.DELAY_SPLASH_SCREEN
import ru.gmasalskikh.ezcs.utils.bitmapFromResources

@KoinApiExtension
class SplashScreenView(
    override val vm: SplashScreenViewModel,
) : BaseView<SplashScreenViewModel>() {

    @Composable
    override fun SetContent() {
        val theme = AmbientAppTheme.current
        val navController = AmbientNavController.current
        rememberCoroutineScope().launch {
            delay(DELAY_SPLASH_SCREEN)
            vm.navigate(navController)
        }
        SplashScreenContent(
            appDescription = stringResource(id = vm.viewState.appDescriptionRes),
            appLogo = bitmapFromResources(id = vm.viewState.appLogoRes),
            appDescriptionColor = theme.colors.onBackground,
        )
    }
}

@Composable
private fun SplashScreenContent(
    appDescription: String,
    appLogo: Bitmap,
    appDescriptionColor: Color,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.TopCenter),
            text = appDescription,
            color = appDescriptionColor
        )
        CoilImage(
            modifier = Modifier
                .fillMaxSize(0.5f)
                .align(Alignment.BottomCenter),
            data = appLogo,
            contentDescription = null,
            loading = {
                CircularProgressIndicator()
            }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0x000000)
@Composable
private fun Preview() {
    SplashScreenContent(
        appDescription = stringResource(id = R.string.app_description),
        appLogo = bitmapFromResources(id = R.drawable.logo),
        appDescriptionColor = Color.White,
    )
}