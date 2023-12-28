package com.duongph.moneynote.data.repo

import com.duongph.moneynote.domain.model.Category
import com.duongph.moneynote.domain.repo.ICategoryRepo

class CategoryRepoImpl(
    private val categoryLocalRepo: ICategoryRepo, private val categoryRemoteRepo: ICategoryRepo
) : ICategoryRepo {
    override suspend fun getCategory(): List<Category> {
        return categoryRemoteRepo.getCategory()
    }
}