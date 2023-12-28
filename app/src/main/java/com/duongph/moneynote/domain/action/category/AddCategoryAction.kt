package com.duongph.moneynote.domain.action.category

import com.duongph.moneynote.action.BaseAction

class AddCategoryAction: BaseAction<AddCategoryAction.AddCategoryRV, Boolean>() {


    class AddCategoryRV : RequestValue {

    }

    override suspend fun execute(requestValue: AddCategoryRV): Boolean {
        return true
    }
}