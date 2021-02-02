package ru.gmasalskikh.ezcs.screens.splash_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.*
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.utils.DELAY_SPLASH_SCREEN

class SplashScreenView(
    override val vm: SplashScreenViewModel,
) : BaseView<SplashScreenViewModel>() {
    @Composable
    override fun setContent() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .clickable(onClick = {
                    vm.navigate(navController)
                })
        ) {
            Text(
                modifier = Modifier.align(Alignment.TopCenter),
                text = stringResource(R.string.app_description),
                color = theme.colors.onBackground
            )
            CoilImage(
                modifier = Modifier
                    .fillMaxSize(0.5f)
                    .align(Alignment.BottomCenter),
                data = R.drawable.logo,
                contentDescription = null
            )
        }
    }

    override fun onActivityResume() {
        super.onActivityResume()
        cs.launch {
            delay(DELAY_SPLASH_SCREEN)
            vm.navigate(navController)
        }
    }
}