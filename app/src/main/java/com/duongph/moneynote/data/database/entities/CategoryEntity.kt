package com.duongph.moneynote.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey @ColumnInfo(name = "id") var id: String? = null,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "color") var color: String? = null,
    @ColumnInfo(name = "resourceIconName") var resourceIconName: String? = null,
    @ColumnInfo(name = "typeMoney") var typeMoney: Int = 0
)