package com.duongph.moneynote.domain.action.category

import com.duongph.moneynote.AppData
import com.duongph.moneynote.action.BaseAction
import com.duongph.moneynote.domain.RepoFactory
import com.duongph.moneynote.domain.model.Category
import com.duongph.moneynote.domain.model.TYPE_MONEY

class GetCategoryAction : BaseAction<GetCategoryAction.GetCategoryActionRV, List<Category>>() {
    override suspend fun execute(requestValue: GetCategoryActionRV): List<Category> {
        val list = AppData.listCategory.ifEmpty {
            RepoFactory.getCategoryRepo().getCategory()
        }
        return list.filter {
            it.typeMoney == requestValue.typeMoney
        }
    }

    class GetCategoryActionRV : RequestValue {
        var typeMoney = TYPE_MONEY.MONEY_OUT
    }
}