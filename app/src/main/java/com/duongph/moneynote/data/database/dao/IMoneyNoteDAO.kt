package com.duongph.moneynote.data.database.dao

import androidx.room.*
import com.duongph.moneynote.data.database.entities.NoteEntity

@Dao
interface IMoneyNoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListNote(noteList: List<NoteEntity>)

    @Transaction
    suspend fun upsertMoneyNote(note: NoteEntity) {
        val id = insertNote(note)
        if (id == -1L) {
            updateMoneyNote(note)
        }
    }

    @Update
    suspend fun updateMoneyNote(message: NoteEntity)

//    @Delete
//    suspend fun deleteMoneyNote(id: String)

    @Query("SELECT * FROM 'note'")
    suspend fun getListNote(): List<NoteEntity>
}