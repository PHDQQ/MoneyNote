package com.duongph.moneynote.data.database.dao

import androidx.room.*
import com.duongph.moneynote.data.database.entities.CategoryEntity

@Dao
interface ICategoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListCategory(category: List<CategoryEntity>): Long

    @Transaction
    suspend fun upsertCategory(category: CategoryEntity) {
        val id = insertCategory(category)
        if (id == -1L) {
            updateCategory(category)
        }
    }

    @Update
    suspend fun updateCategory(category: CategoryEntity)

    @Query("SELECT * FROM `category`")
    suspend fun getListCategory(): List<CategoryEntity>
}