package com.duongph.moneynote.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import androidx.room.Update
import com.duongph.moneynote.data.database.entities.NoteEntity

@Dao
interface IMoneyDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: NoteEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListNote(noteList: List<NoteEntity>)

    @Transaction
    fun upsertMessage(note: NoteEntity) {
        val id = insertNote(note)
        if (id == -1L) {
            updateMessage(note)
        }
    }

    @Update
    fun updateMessage(message: NoteEntity)
}