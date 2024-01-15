package com.duongph.moneynote.domain.repo

import com.duongph.moneynote.data.model.NoteResponse
import com.duongph.moneynote.domain.model.MoneyNote


interface IMoneyNoteRepo {
    suspend fun getMoneyNote(): List<MoneyNote>

    suspend fun getMoneyNoteByTime(time1: Long, time2: Long): List<MoneyNote>
    suspend fun addMoneyNote(note: MoneyNote): NoteResponse
    suspend fun updateMoneyNote(note: MoneyNote): Boolean
    suspend fun deleteMoneyNote(noteId: String): Boolean

    suspend fun syncMoneyNote(): Boolean
    suspend fun addMoneyNotes(notes: List<MoneyNote>): Boolean
}