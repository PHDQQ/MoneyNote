package com.duongph.moneynote.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey @ColumnInfo(name = "id") var id: String? = null,
    @ColumnInfo(name = "money") var money: String? = null,
    @ColumnInfo(name = "date") var date: Long? = null,
    @ColumnInfo(name = "note") var note: String? = null,
    @ColumnInfo(name = "idCategory") var idCategory: String? = null,
    @ColumnInfo(name = "typeMoney") var typeMoney: Int = 0
)