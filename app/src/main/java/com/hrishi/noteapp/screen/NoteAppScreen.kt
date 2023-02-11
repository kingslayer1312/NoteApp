package com.hrishi.noteapp.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hrishi.noteapp.R
import com.hrishi.noteapp.components.NoteInputText

//colors
val raisinBlack = Color(0xFF1A1423)
val darkPurple = Color(0xFF372549)
val eggplant = Color(0xFF774C60)
val redwood = Color(0xFFB75D69)
val paleDogwood = Color(0xFFEACDC2)

@Composable
fun NoteAppScreen() {
    Column(modifier = Modifier.padding(0.dp)) {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        },
            actions = {
                Icon(imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Icon")
            },
            backgroundColor = raisinBlack,
            contentColor = Color.White
        )

        Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
            NoteInputText(text = "", label = "Title", onTextChange = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteAppScreenPreview() {
    NoteAppScreen()
}