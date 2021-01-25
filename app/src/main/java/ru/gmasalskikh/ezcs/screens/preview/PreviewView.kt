package ru.gmasalskikh.ezcs.screens.preview

import android.util.Log
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.gmasalskikh.ezcs.screens.preview.widgets.PreviewItem

@Composable
fun PreviewView() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyRow {
            items(items = (0..10).toList()) {
                PreviewItem("AAAA = $it")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview_PreviewView() {
    PreviewView()
}