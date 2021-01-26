package ru.gmasalskikh.ezcs.screens.preview.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage
import java.util.*
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.ui.theme.orange
import ru.gmasalskikh.ezcs.ui.theme.transparent

@Composable
fun PreviewItem(
    name: String = "",
//    imageBitmap: ImageBitmap,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier
                // aspectRatio 9:16 = 0.5625f
                .aspectRatio(0.5625f)
                .fillMaxSize(),
            border = BorderStroke(
                width = 4.dp,
                color = orange
            ),
            shape = RoundedCornerShape(10.dp),
            color = transparent
        ) {
//            CoilImage(
//                data = R.drawable.ct_logo,
//                contentScale = ContentScale.FillHeight,
//                loading = {
//                    CircularProgressIndicator()
//                }
//            )


//            Image(
//                modifier = Modifier.fillMaxHeight(),
//                contentScale = ContentScale.FillHeight,
//                bitmap = imageFromResource(AmbientContext.current.resources, R.drawable.t_logo)
//            )
        }

    }
}