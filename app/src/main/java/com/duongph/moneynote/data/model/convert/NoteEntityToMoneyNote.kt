package com.duongph.moneynote.data.model.convert

import com.duongph.moneynote.*
import com.duongph.moneynote.data.converter.IConverter
import com.duongph.moneynote.data.database.entities.NoteEntity
import com.duongph.moneynote.domain.model.DateTimeObject
import com.duongph.moneynote.domain.model.MoneyNote
import com.duongph.moneynote.domain.model.convertEnumTypeMoney

class NoteEntityToMoneyNote : IConverter<NoteEntity, MoneyNote> {
    override fun convert(source: NoteEntity): MoneyNote {
        return MoneyNote().apply {
            id = source.id
            note = source.note
            money = source.money
            dateTimeObject = DateTimeObject().apply {
                date = source.date
                dayString = source.date.getToDay()
                monthString = source.date.getToDay2().getCurrentMonth()
                yearString = source.date.getToDay2().getCurrentYear()
            }
            typeMoney = source.typeMoney.convertEnumTypeMoney()
            category = AppData.listCategory.find { it.id == source.idCategory }
        }
    }
}