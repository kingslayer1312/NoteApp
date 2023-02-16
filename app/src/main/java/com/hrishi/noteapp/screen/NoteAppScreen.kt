package com.hrishi.noteapp.screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hrishi.noteapp.R
import com.hrishi.noteapp.components.NoteButton
import com.hrishi.noteapp.components.NoteInputText
import com.hrishi.noteapp.data.NotesDataSource
import com.hrishi.noteapp.model.Note
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle


var x = 0

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

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(0.dp)
            .background(colorResource(id = R.color.butterscotch))
    ) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    fontSize = 23.sp,
                    fontWeight = FontWeight(800)
                )
            },
            actions = {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Icon"
                )
            },
            backgroundColor = colorResource(id = R.color.richBlack),
            contentColor = colorResource(id = R.color.butterscotch)
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                    if (title.isNotEmpty() && description.isNotEmpty()) {
                        // save or add to list
                        onAddNote(Note(title = title, description = description))
                        title = ""
                        description = ""
                        Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                    }
                })

            Divider(modifier = Modifier.padding(10.dp), thickness = 3.dp)
            LazyColumn {
                items(notes) { note ->
                    NoteRow(note = note, onRemoveNote = onRemoveNote)
                }
            }
        }
    }
}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    //onNoteClicked: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {
    Surface(
        modifier
            .padding(horizontal = 6.dp, vertical = 4.dp)
            .clip(
                RoundedCornerShape(
                    topEnd = 20.dp,
                    bottomEnd = 20.dp,
                    topStart = 20.dp,
                    bottomStart = 20.dp
                )
            )
            .fillMaxWidth(),
        color = colorResource(id = R.color.richBlack),
        elevation = 6.dp
    ) {
        Column(
            modifier
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = note.title,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = colorResource(id = R.color.butterscotch)
            )

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 0.dp, top = 10.dp, bottom = 10.dp),
                color = colorResource(id = R.color.ecru),
                thickness = 3.dp
            )
            Text(
                text = note.description,
                style = MaterialTheme.typography.subtitle1,
                color = colorResource(id = R.color.ecru)
            )
            Text(
                modifier = Modifier.padding(top = 7.dp, bottom = 7.dp),
                text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM y")),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.ecru)
            )

            NoteButton(
                text = "discard",
                onClick = {
                    onRemoveNote(note)
                },
                backgroundColor = colorResource(id = R.color.alloyOrange),
                contentColor = colorResource(id = R.color.charcoal)
            )

        }
    }

}

@Preview(showBackground = true)
@Composable
fun NoteAppScreenPreview() {
    NoteAppScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}