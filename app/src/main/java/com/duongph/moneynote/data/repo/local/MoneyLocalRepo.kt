package com.duongph.moneynote.data.repo.local

import com.duongph.moneynote.domain.model.MoneyNote
import com.duongph.moneynote.domain.repo.IMoneyNoteRepo

class MoneyLocalRepo : IMoneyNoteRepo {
    override suspend fun getMoneyNote(): List<MoneyNote> {
        return emptyList()
    }

    override suspend fun addMoneyNote(note: MoneyNote): Boolean {
        return true
    }

    override suspend fun deleteMoneyNote(noteId: String): Boolean {
        return true
    }

    override suspend fun updateMoneyNote(note: MoneyNote): Boolean {
        return true
    }
}