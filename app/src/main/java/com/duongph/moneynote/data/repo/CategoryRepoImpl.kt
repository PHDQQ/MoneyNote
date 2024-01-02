package com.duongph.moneynote.data.repo

import com.duongph.moneynote.domain.model.Category
import com.duongph.moneynote.domain.repo.ICategoryRepo

class CategoryRepoImpl(
    private val categoryLocalRepo: ICategoryRepo, private val categoryRemoteRepo: ICategoryRepo
) : ICategoryRepo {
    override suspend fun getCategory(): List<Category> {
        val list = categoryRemoteRepo.getCategory()
        addCategory(list)
        return categoryLocalRepo.getCategory()
    }

    override suspend fun addCategory(listCategory: List<Category>): Boolean {
        return categoryLocalRepo.addCategory(listCategory)
    }
}