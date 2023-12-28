package com.duongph.moneynote.data.repo.local

import com.duongph.moneynote.domain.model.Category
import com.duongph.moneynote.domain.repo.ICategoryRepo

class CategoryLocalRepo : ICategoryRepo {
    override suspend fun getCategory(): List<Category> {
        return emptyList()
    }
}