package com.duongph.moneynote.domain.action.category

import com.duongph.moneynote.action.BaseAction
import com.duongph.moneynote.domain.RepoFactory
import com.duongph.moneynote.domain.model.Category

class AddCategoryAction: BaseAction<AddCategoryAction.AddCategoryRV, Boolean>() {

    class AddCategoryRV : RequestValue {
       val listCategory = ArrayList<Category>()
    }

    override suspend fun execute(requestValue: AddCategoryRV): Boolean {
        RepoFactory.getCategoryRepo().addCategory(requestValue.listCategory)
        return true
    }
}