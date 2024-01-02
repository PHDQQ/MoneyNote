package com.duongph.moneynote.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey @ColumnInfo(name = "id") var id: String,
    @ColumnInfo(name = "money") var money: String,
    @ColumnInfo(name = "date") var date: Long,
    @ColumnInfo(name = "note") var note: String,
    @ColumnInfo(name = "idCategory") var idCategory: String,
    @ColumnInfo(name = "typeMoney") var typeMoney: Int = 0
)