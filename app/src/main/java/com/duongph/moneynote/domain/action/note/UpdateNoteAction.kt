package com.duongph.moneynote.domain.action.note

import com.duongph.moneynote.action.BaseAction
import com.duongph.moneynote.domain.RepoFactory
import com.duongph.moneynote.domain.model.MoneyNote

class UpdateNoteAction : BaseAction<UpdateNoteAction.UpdateNoteActionRV, Boolean>() {
    override suspend fun execute(requestValue: UpdateNoteActionRV): Boolean {
        RepoFactory.getNoteRepo().updateMoneyNote(requestValue.moneyNote!!)
        return true
    }

    class UpdateNoteActionRV : RequestValue {
        var moneyNote: MoneyNote? = null
    }
}