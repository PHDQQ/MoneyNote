package com.duongph.moneynote.data.model.convert

import com.duongph.moneynote.data.converter.IConverter
import com.duongph.moneynote.data.database.entities.NoteEntity
import com.duongph.moneynote.domain.model.MoneyNote

class MoneyNoteToNoteEntity : IConverter<MoneyNote, NoteEntity> {
    override fun convert(source: MoneyNote): NoteEntity {
        return NoteEntity(
            id = source.id!!,
            note = source.note ?: "",
            money = source.money!!,
            date = source.dateTimeObject!!.date ?: 0,
            idCategory = source.category?.id ?: "",
            typeMoney = source.typeMoney.value
        )
    }

}