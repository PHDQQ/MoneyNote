package com.duongph.moneynote.data.database.entities

import androidx.room.Embedded
import androidx.room.Relation

class CategoryWithNoteEntity {
    @Embedded
    var category: CategoryEntity?= null
    @Relation(parentColumn = "id", entityColumn = "idCategory")
    var listNote: List<NoteEntity> = arrayListOf()
}