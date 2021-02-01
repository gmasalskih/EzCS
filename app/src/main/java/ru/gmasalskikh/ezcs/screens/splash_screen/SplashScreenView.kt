package ru.gmasalskikh.ezcs.screens.splash_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.ui.theme.AppTheme
import ru.gmasalskikh.ezcs.utils.AmbientAppTheme
import ru.gmasalskikh.ezcs.utils.AmbientNavController
import ru.gmasalskikh.ezcs.utils.DELAY_SPLASH_SCREEN

@Composable
fun SplashScreenView(
    navController: NavController = AmbientNavController.current,
    cs: CoroutineScope = rememberCoroutineScope(),
    theme: AppTheme = AmbientAppTheme.current,
    vm: SplashScreenViewModel = getViewModel()
) {
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
    cs.launch {
        delay(DELAY_SPLASH_SCREEN)
        vm.navigate(navController)
    }
}