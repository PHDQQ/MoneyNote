package com.duongph.moneynote.data.repo

import com.duongph.moneynote.domain.model.Category
import com.duongph.moneynote.domain.model.CategoryWithNote
import com.duongph.moneynote.domain.repo.ICategoryRepo

class CategoryRepoImpl(
    private val categoryLocalRepo: ICategoryRepo, private val categoryRemoteRepo: ICategoryRepo
) : ICategoryRepo {
    override suspend fun getCategory(): List<Category> {
        return categoryLocalRepo.getCategory()
    }

    override suspend fun addCategory(listCategory: List<Category>): Boolean {
        return categoryRemoteRepo.addCategory(listCategory)
    }

    override suspend fun getCategoryWithNote(time1: Long, time2: Long): List<CategoryWithNote> {
        return categoryLocalRepo.getCategoryWithNote(time1, time2)
    }

    override suspend fun syncCategory(): Boolean {
        categoryLocalRepo.addCategory(categoryRemoteRepo.getCategory())
        return true
    }
}