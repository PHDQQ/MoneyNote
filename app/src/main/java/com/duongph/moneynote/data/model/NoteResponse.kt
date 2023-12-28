package com.duongph.moneynote.data.model

class NoteResponse(
    var id: String? = null,
    var money: String? = null,
    var date: Long? = null,
    var note: String? = null,
    var idCategory: String? = null,
    var moneyOut: Boolean = true
)