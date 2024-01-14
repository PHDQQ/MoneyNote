package com.duongph.moneynote.domain.action.sync

import android.util.Log
import com.duongph.moneynote.action.BaseAction
import com.duongph.moneynote.domain.RepoFactory

class SyncDataAction : BaseAction<BaseAction.RequestValue, Boolean>() {

    override suspend fun execute(requestValue: RequestValue): Boolean {
        Log.d("duongph", "SyncDataAction category: ")
        RepoFactory.getCategoryRepo().syncCategory()
        Log.d("duongph", "SyncDataAction note: ")
        RepoFactory.getNoteRepo().syncMoneyNote()
        return true
    }
}