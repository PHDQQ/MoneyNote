package com.duongph.moneynote.domain.repo

import com.duongph.moneynote.domain.model.Category

interface ICategoryRepo {
    suspend fun getCategory(): List<Category>

}