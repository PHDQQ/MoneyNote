package com.duongph.moneynote.data.database.entities.converter

import com.duongph.moneynote.data.converter.IConverter
import com.duongph.moneynote.data.converter.ListConverter
import com.duongph.moneynote.data.database.entities.CategoryWithNoteEntity
import com.duongph.moneynote.data.model.convert.CategoryEntityToCategory
import com.duongph.moneynote.data.model.convert.NoteEntityToMoneyNote
import com.duongph.moneynote.domain.model.CategoryWithNote

class CategoryWithNoteEntityToCategoryWithNote: IConverter<CategoryWithNoteEntity, CategoryWithNote> {
    override fun convert(source: CategoryWithNoteEntity): CategoryWithNote {
        return CategoryWithNote().apply {
            category = CategoryEntityToCategory().convert(source.category!!)
            listNote = ListConverter(NoteEntityToMoneyNote()).convert(source.listNote)
        }
    }
}