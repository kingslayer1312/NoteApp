package com.hrishi.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hrishi.noteapp.data.NotesDataSource
import com.hrishi.noteapp.model.Note
import com.hrishi.noteapp.screen.NoteAppScreen
import com.hrishi.noteapp.ui.theme.NoteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val notes = remember {
                        mutableStateListOf<Note>()
                    }
                    NoteAppScreen(notes = notes,
                        onAddNote = {
                            notes.add(it)
                        },
                        onRemoveNote = {
                            notes.remove(it)
                        })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NoteAppTheme {

    }
}