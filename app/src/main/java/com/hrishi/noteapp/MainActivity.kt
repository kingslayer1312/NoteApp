package com.hrishi.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hrishi.noteapp.data.NotesDataSource
import com.hrishi.noteapp.model.Note
import com.hrishi.noteapp.screen.NoteAppScreen
import com.hrishi.noteapp.screen.NoteViewModel
import com.hrishi.noteapp.ui.theme.NoteAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val noteViewModel: NoteViewModel by viewModels()
                    NotesApp(noteViewModel = noteViewModel)
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun NotesApp(noteViewModel: NoteViewModel = viewModel()) {
    val notesList = noteViewModel.getAllNotes()
    NoteAppScreen(notes = notesList,
        onAddNote = {
            noteViewModel.addNote(it)
        },
        onRemoveNote = {
            noteViewModel.removeNote(it)
        })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NoteAppTheme {

    }
}