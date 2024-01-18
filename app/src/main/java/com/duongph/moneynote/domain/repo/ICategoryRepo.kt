package com.duongph.moneynote.domain.repo

import com.duongph.moneynote.domain.model.Category
import com.duongph.moneynote.domain.model.CategoryWithNote

interface ICategoryRepo {
    suspend fun getCategory(): List<Category>
    suspend fun getCategoryWithNote(time1: Long, time2: Long): List<CategoryWithNote>

    suspend fun addCategory(listCategory: List<Category>): Boolean

    suspend fun syncCategory(): Boolean

}