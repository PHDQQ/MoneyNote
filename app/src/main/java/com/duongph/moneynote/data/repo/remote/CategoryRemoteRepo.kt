package com.duongph.moneynote.data.repo.remote

import com.duongph.moneynote.AppData
import com.duongph.moneynote.common.Const
import com.duongph.moneynote.data.FirebaseModule
import com.duongph.moneynote.data.converter.ListConverter
import com.duongph.moneynote.data.model.CategoryResponse
import com.duongph.moneynote.data.model.convert.CategoryResponseToCategory
import com.duongph.moneynote.domain.model.Category
import com.duongph.moneynote.domain.repo.ICategoryRepo
import kotlinx.coroutines.tasks.await

class CategoryRemoteRepo : ICategoryRepo {
    override suspend fun getCategory(): List<Category> {
        val dataSnap =
            FirebaseModule.provideFireStoreInstance().collection(Const.CATEGORY).get().await()
        val listCate = arrayListOf<CategoryResponse>()
        dataSnap?.forEach {
            listCate.add(it.toObject(CategoryResponse::class.java).apply {
                id = it.id
            })
        }
        AppData.listCategory.clear()
        AppData.listCategory.addAll(ListConverter(CategoryResponseToCategory()).convert(listCate))
        return AppData.listCategory
    }

    override suspend fun addCategory(listCategory: List<Category>): Boolean {
        return true
    }
}