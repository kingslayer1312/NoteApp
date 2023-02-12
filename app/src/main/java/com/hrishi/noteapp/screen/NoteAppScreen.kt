package com.hrishi.noteapp.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hrishi.noteapp.R
import com.hrishi.noteapp.components.NoteButton
import com.hrishi.noteapp.components.NoteInputText
import com.hrishi.noteapp.model.Note

@Composable
fun NoteAppScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.padding(0.dp),) {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        },
            actions = {
                Icon(imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Icon")
            },
            backgroundColor = colorResource(id = R.color.raisinBlack),
            contentColor = colorResource(id = R.color.paleDogwood)
        )

        Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
            NoteInputText(
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = title,
                label = "Title",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) title = it
                })
            NoteInputText(
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = description,
                label = "Add a note",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) description = it
                })
            NoteButton(
                text = "Save",
                onClick = {
                    if (title.isNotEmpty() && description.isNotEmpty()){
                        // save or add to list
                        title = ""
                        description = ""
                    }
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteAppScreenPreview() {
    NoteAppScreen(notes = emptyList(), onAddNote = {}, onRemoveNote = {})
}