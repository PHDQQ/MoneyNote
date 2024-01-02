package com.duongph.moneynote.data.repo.local

import com.duongph.moneynote.data.converter.ListConverter
import com.duongph.moneynote.data.database.MoneyDatabase
import com.duongph.moneynote.data.model.convert.MoneyNoteToNoteEntity
import com.duongph.moneynote.data.model.convert.NoteEntityToMoneyNote
import com.duongph.moneynote.domain.model.MoneyNote
import com.duongph.moneynote.domain.repo.IMoneyNoteRepo

class MoneyLocalRepo : IMoneyNoteRepo {
    override suspend fun getMoneyNote(): List<MoneyNote> {
        val list = MoneyDatabase.g().moneyNoteDao().getListNote()
        return ListConverter(NoteEntityToMoneyNote()).convert(list)
    }

    override suspend fun addMoneyNote(note: MoneyNote): Boolean {
        MoneyDatabase.g().moneyNoteDao().insertNote(MoneyNoteToNoteEntity().convert(note))
        return true
    }

    override suspend fun deleteMoneyNote(noteId: String): Boolean {
        return true
    }

    override suspend fun updateMoneyNote(note: MoneyNote): Boolean {
        return true
    }
}