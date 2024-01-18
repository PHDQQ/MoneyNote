package com.duongph.moneynote.data.database.dao

import androidx.room.*
import com.duongph.moneynote.data.database.entities.CategoryEntity
import com.duongph.moneynote.data.database.entities.CategoryWithNoteEntity

@Dao
interface ICategoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListCategory(category: List<CategoryEntity>)

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

    //    @Query("SELECT * FROM `note` INNER JOIN `category` ON note.idCategory = category.id where note.date >=:time1 AND note.date<= :time2")
    @Transaction
    @Query("SELECT * FROM `category` where note.date >= :time1 AND note.date<= :time2")
    suspend fun getListCategoryWithNote(time1: Long, time2: Long): List<CategoryWithNoteEntity>
}