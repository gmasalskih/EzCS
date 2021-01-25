package ru.gmasalskikh.ezcs.screens.preview.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun PreviewItem(
    name: String = "",
//    imageBitmap: ImageBitmap,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = name.toUpperCase(Locale.getDefault()),
            textAlign = TextAlign.Center,
            color = Color.White
        )
        Surface(
            modifier = Modifier
                .aspectRatio(0.5f)
                .fillMaxSize(),
            border = BorderStroke(
                width = 5.dp,
                color = Color.Red
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            CoilImage(
                data = R.drawable.ct_logo,
                contentScale = ContentScale.FillHeight
            )
                
            
//            Image(
//                modifier = Modifier.fillMaxHeight(),
//                contentScale = ContentScale.FillHeight,
//                bitmap = imageFromResource(AmbientContext.current.resources, R.drawable.t_logo)
//            )
        }

    }
}