package com.duongph.moneynote.domain.action.note

import com.duongph.moneynote.action.BaseAction
import com.duongph.moneynote.domain.RepoFactory

class DeleteNoteAction : BaseAction<DeleteNoteAction.DeleteNoteRV, Boolean>() {
    class DeleteNoteRV : RequestValue {
        var noteId: String = ""
    }

    override suspend fun execute(requestValue: DeleteNoteRV): Boolean {
        RepoFactory.getNoteRepo().deleteMoneyNote(requestValue.noteId)
        return true
    }
}