package com.duongph.moneynote.data.model.convert

import com.duongph.moneynote.data.converter.IConverter
import com.duongph.moneynote.data.model.CategoryResponse
import com.duongph.moneynote.domain.model.Category
import com.duongph.moneynote.domain.model.TYPE_MONEY
import com.duongph.moneynote.domain.model.convertEnumTypeMoney

class CategoryResponseToCategory : IConverter<CategoryResponse, Category> {
    override fun convert(source: CategoryResponse): Category {
        return Category().apply {
            id = source.id
            name = source.name
            resourceName = source.resourceName
            color = source.color
            typeMoney = if (source.typeMoney != null) {
                source.typeMoney!!.convertEnumTypeMoney()
            } else if (source.isMoneyOut) TYPE_MONEY.MONEY_OUT else TYPE_MONEY.MONEY_IN
        }
    }
}