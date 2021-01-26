package ru.gmasalskikh.ezcs.screens.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.ui.widget.AppBackground
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.utils.AmbientNavController
import ru.gmasalskikh.ezcs.utils.DELAY_SPLASH_SCREEN

@Composable
fun SplashScreenView(
    navController: NavController = AmbientNavController.current
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.TopCenter),
            text = stringResource(R.string.app_description),
            color = MaterialTheme.colors.background
        )
        Image(
            modifier = Modifier
                .preferredSize(240.dp)
                .align(Alignment.BottomCenter),
            bitmap = imageFromResource(AmbientContext.current.resources, R.drawable.app_logo)
        )
    }
    rememberCoroutineScope().launch {
        delay(DELAY_SPLASH_SCREEN)
        navController.navigate(TargetNavigation.PREVIEW.name)
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview_SplashScreen() {
    AppBackground(false) {
        SplashScreenView()
    }
}