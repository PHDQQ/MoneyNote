package com.duongph.moneynote.data.repo

import android.util.Log
import com.duongph.moneynote.AppData
import com.duongph.moneynote.data.model.NoteResponse
import com.duongph.moneynote.data.model.convert.NoteResponseToMoneyNote
import com.duongph.moneynote.domain.model.MoneyNote
import com.duongph.moneynote.domain.model.TYPE_MONEY
import com.duongph.moneynote.domain.repo.IMoneyNoteRepo

class MoneyRepoImpl(
    private val moneyRemoteRepo: IMoneyNoteRepo,
    private val moneyLocalRepo: IMoneyNoteRepo
) : IMoneyNoteRepo {
    override suspend fun getMoneyNote(): List<MoneyNote> {
        val list = moneyLocalRepo.getMoneyNote()
        list.forEach {
            if (it.typeMoney == TYPE_MONEY.MONEY_IN) {
                AppData.listNoteIn.add(it)
            } else {
                AppData.listNoteOut.add(it)
            }
        }
        return list
    }

    override suspend fun addMoneyNote(note: MoneyNote): NoteResponse {
        val money = moneyRemoteRepo.addMoneyNote(note)
        moneyLocalRepo.addMoneyNote(NoteResponseToMoneyNote().convert(money))
        Log.d("duongph", "addMoneyNote done: ")
        return NoteResponse()
    }

    override suspend fun updateMoneyNote(note: MoneyNote): Boolean {
        return moneyRemoteRepo.updateMoneyNote(note)
    }

    override suspend fun deleteMoneyNote(noteId: String): Boolean {
        moneyRemoteRepo.deleteMoneyNote(noteId)
        moneyLocalRepo.deleteMoneyNote(noteId)
        return true
    }

    override suspend fun getMoneyNoteByTime(time1: Long, time2: Long): List<MoneyNote> {
        return moneyLocalRepo.getMoneyNoteByTime(time1, time2)
    }

    override suspend fun syncMoneyNote(): Boolean {
        addMoneyNotes(moneyRemoteRepo.getMoneyNote())
        return true
    }

    override suspend fun addMoneyNotes(notes: List<MoneyNote>): Boolean {
        moneyLocalRepo.addMoneyNotes(notes)
        return true
    }
}