package com.duongph.moneynote.domain.repo

import com.duongph.moneynote.domain.model.MoneyNote


interface IMoneyNoteRepo {
    suspend fun getMoneyNote(): List<MoneyNote>
    suspend fun addMoneyNote(note: MoneyNote): Boolean
    suspend fun updateMoneyNote(note: MoneyNote): Boolean
    suspend fun deleteMoneyNote(noteId: String): Boolean

    suspend fun syncMoneyNote(): Boolean
    suspend fun addMoneyNotes(notes: List<MoneyNote>): Boolean
}