package com.duongph.moneynote.domain.action.sync

import com.duongph.moneynote.action.BaseAction
import com.duongph.moneynote.domain.RepoFactory

class SyncDataAction : BaseAction<BaseAction.RequestValue, Boolean>() {

    override suspend fun execute(requestValue: RequestValue): Boolean {
        RepoFactory.getCategoryRepo().getCategory()
        return true
    }
}