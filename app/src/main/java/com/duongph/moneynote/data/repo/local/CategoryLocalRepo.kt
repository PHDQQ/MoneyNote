package com.duongph.moneynote.data.repo.local

import com.duongph.moneynote.data.converter.ListConverter
import com.duongph.moneynote.data.database.MoneyDatabase
import com.duongph.moneynote.data.database.entities.CategoryEntity
import com.duongph.moneynote.data.model.convert.CategoryEntityToCategory
import com.duongph.moneynote.data.model.convert.CategoryToCategoryEntity
import com.duongph.moneynote.domain.model.Category
import com.duongph.moneynote.domain.repo.ICategoryRepo

class CategoryLocalRepo : ICategoryRepo {
    override suspend fun getCategory(): List<Category> {
        val list = MoneyDatabase.g().categoryDao().getListCategory()
        return ListConverter(CategoryEntityToCategory()).convert(list)
    }

    override suspend fun syncCategory(): Boolean {
        return true
    }

    override suspend fun addCategory(listCategory: List<Category>): Boolean {
        MoneyDatabase.g().categoryDao()
            .insertListCategory(ListConverter(CategoryToCategoryEntity()).convert(listCategory))
        return true
    }
}