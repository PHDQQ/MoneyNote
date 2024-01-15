package com.duongph.moneynote.data.database.dao

import androidx.room.*
import com.duongph.moneynote.data.database.entities.NoteEntity

@Dao
interface IMoneyNoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListNote(noteList: List<NoteEntity>)

    @Query("Delete FROM 'note' where id =:id")
    suspend fun deleteNote(id: String)

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

    @Query("SELECT * FROM 'note' where date >=:time1 AND date<= :time2")
    suspend fun getListNoteByTime(time1: Long, time2: Long): List<NoteEntity>
}