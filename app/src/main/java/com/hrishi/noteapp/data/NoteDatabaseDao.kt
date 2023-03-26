package com.hrishi.noteapp.data

import androidx.room.*
import com.hrishi.noteapp.model.Note

@Dao
interface NoteDatabaseDao {
    @Query(value = "SELECT * from notes_table")
    fun getNotes() : List<Note>

    @Query(value = "SELECT * from notes_table where id =:id")
    fun getNoteById(id: String ): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(note: Note)

    @Query("DELETE from notes_table")
    fun deleteAll()

    @Delete
    fun deleteOneItem(note: Note)
}
