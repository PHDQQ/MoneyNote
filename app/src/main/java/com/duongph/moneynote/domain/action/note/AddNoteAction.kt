package com.duongph.moneynote.domain.action.note

import com.duongph.moneynote.action.BaseAction
import com.duongph.moneynote.domain.RepoFactory
import com.duongph.moneynote.domain.model.MoneyNote

class AddNoteAction : BaseAction<AddNoteAction.AddNoteActionRV, Boolean>() {
    override suspend fun execute(requestValue: AddNoteActionRV): Boolean {
        RepoFactory.getNoteRepo().addMoneyNote(requestValue.moneyNote!!)
        return true
    }

    class AddNoteActionRV : RequestValue {
        var moneyNote: MoneyNote? = null
    }
}