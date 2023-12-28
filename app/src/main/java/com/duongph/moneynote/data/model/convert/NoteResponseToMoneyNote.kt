package com.duongph.moneynote.data.model.convert

import com.duongph.moneynote.AppData
import com.duongph.moneynote.data.converter.IConverter
import com.duongph.moneynote.data.model.NoteResponse
import com.duongph.moneynote.domain.model.DateTimeObject
import com.duongph.moneynote.domain.model.MoneyNote
import com.duongph.moneynote.domain.model.TYPE_MONEY
import com.duongph.moneynote.getCurrentMonth
import com.duongph.moneynote.getCurrentYear
import com.duongph.moneynote.getToDay
import com.duongph.moneynote.getToDay2

class NoteResponseToMoneyNote : IConverter<NoteResponse, MoneyNote> {
    override fun convert(source: NoteResponse): MoneyNote {
        return MoneyNote().apply {
            id = source.id
            note = source.note
            money = source.money
            dateTimeObject = DateTimeObject().apply {
                date = source.date
                dayString = source.date!!.getToDay()
                monthString = source.date!!.getToDay2().getCurrentMonth()
                yearString = source.date!!.getToDay2().getCurrentYear()
            }
            typeMoney = if (source.moneyOut) TYPE_MONEY.MONEY_OUT else TYPE_MONEY.MONEY_IN
            category = AppData.listCategory.find { it.id == source.idCategory }
        }

    }
}