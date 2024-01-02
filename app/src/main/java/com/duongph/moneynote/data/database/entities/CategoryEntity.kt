package com.duongph.moneynote.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey @ColumnInfo(name = "id") var id: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "color") var color: String,
    @ColumnInfo(name = "resourceIconName") var resourceIconName: String,
    @ColumnInfo(name = "typeMoney") var typeMoney: Int = 0
)