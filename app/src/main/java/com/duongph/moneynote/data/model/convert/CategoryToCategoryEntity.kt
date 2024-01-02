package com.duongph.moneynote.data.model.convert

import com.duongph.moneynote.data.converter.IConverter
import com.duongph.moneynote.data.database.entities.CategoryEntity
import com.duongph.moneynote.domain.model.Category

class CategoryToCategoryEntity : IConverter<Category, CategoryEntity> {
    override fun convert(source: Category): CategoryEntity {
        return CategoryEntity().apply {
            id = source.id
            name = source.name
            resourceIconName = source.resourceName
            color = source.color
            typeMoney = source.typeMoney.value
        }
    }
}