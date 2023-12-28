package com.duongph.moneynote.data.model.convert

import com.duongph.moneynote.data.converter.IConverter
import com.duongph.moneynote.data.model.NoteResponse
import com.duongph.moneynote.domain.model.MoneyNote
import com.duongph.moneynote.domain.model.TYPE_MONEY

class MoneyNoteToNoteResponse: IConverter<MoneyNote, NoteResponse> {
    override fun convert(source: MoneyNote): NoteResponse {
        return NoteResponse().apply {
            id = source.id
            money = source.money
            date = source.dateTimeObject!!.date
            note = source.note
            idCategory = source.category!!.id
            moneyOut = source.typeMoney == TYPE_MONEY.MONEY_OUT
        }
    }
}