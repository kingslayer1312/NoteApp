package com.hrishi.noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hrishi.noteapp.model.Note
import com.hrishi.noteapp.util.DateConverter
import com.hrishi.noteapp.util.UUIDConverter

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun noteDao(): NoteDatabaseDao
}