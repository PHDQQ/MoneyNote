package com.duongph.moneynote.data.model.convert

import com.duongph.moneynote.data.converter.IConverter
import com.duongph.moneynote.data.database.entities.CategoryEntity
import com.duongph.moneynote.domain.model.Category
import com.duongph.moneynote.domain.model.convertEnumTypeMoney

class CategoryEntityToCategory : IConverter<CategoryEntity, Category> {
    override fun convert(source: CategoryEntity): Category {
        return Category().apply {
            id = source.id
            name = source.name
            resourceName = source.resourceIconName
            color = source.color
            typeMoney = source.typeMoney.convertEnumTypeMoney()
        }
    }
}