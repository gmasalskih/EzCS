package ru.gmasalskikh.ezcs.screens.splash_screen

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
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
    sp: SharedPreferences = get(),
    vm: SplashScreenViewModel = getViewModel { parametersOf(sp) }
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
        Image(
            modifier = Modifier
                .preferredSize(240.dp)
                .align(Alignment.BottomCenter),
            bitmap = imageFromResource(AmbientContext.current.resources, R.drawable.app_logo),
            contentDescription = null
        )
    }
    cs.launch {
        delay(DELAY_SPLASH_SCREEN)
        vm.navigate(navController)
    }
}