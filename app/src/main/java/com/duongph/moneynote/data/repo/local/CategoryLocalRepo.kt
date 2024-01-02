package com.duongph.moneynote.data.repo.local

import com.duongph.moneynote.data.converter.ListConverter
import com.duongph.moneynote.data.database.MoneyDatabase
import com.duongph.moneynote.data.model.convert.CategoryToCategoryEntity
import com.duongph.moneynote.domain.model.Category
import com.duongph.moneynote.domain.repo.ICategoryRepo

class CategoryLocalRepo : ICategoryRepo {
    override suspend fun getCategory(): List<Category> {
        return emptyList()
    }

    override suspend fun addCategory(listCategory: List<Category>): Boolean {
        MoneyDatabase.g().categoryDao()
            .insertListCategory(ListConverter(CategoryToCategoryEntity()).convert(listCategory))
        return true
    }
}