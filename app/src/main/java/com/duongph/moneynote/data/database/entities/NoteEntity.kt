package com.duongph.moneynote.data.database.entities

class NoteEntity(
    var id: String? = null,
    val money: String? = null,
    val date: Long? = null,
    val note: String? = null,
    val idCategory: String? = null,
    val moneyOut: Boolean = true
)