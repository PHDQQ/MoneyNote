package com.duongph.moneynote.data.repo.remote

import com.duongph.moneynote.common.Const
import com.duongph.moneynote.data.FirebaseModule
import com.duongph.moneynote.data.converter.ListConverter
import com.duongph.moneynote.data.model.NoteResponse
import com.duongph.moneynote.data.model.convert.MoneyNoteToNoteResponse
import com.duongph.moneynote.data.model.convert.NoteResponseToMoneyNote
import com.duongph.moneynote.domain.model.MoneyNote
import com.duongph.moneynote.domain.repo.IMoneyNoteRepo
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await

class MoneyRemoteRepo : IMoneyNoteRepo {
    private val database = FirebaseModule.provideFireStoreInstance()
    override suspend fun getMoneyNote(): List<MoneyNote> {
        val dataSnap =
            FirebaseModule.provideFireStoreInstance().collection(Const.NOTE).get().await()
        val list = arrayListOf<NoteResponse>()
        dataSnap?.forEach {
            list.add(it.toObject(NoteResponse::class.java).apply {
                id = it.id
            })
        }
        return ListConverter(NoteResponseToMoneyNote()).convert(list)
    }

    override suspend fun updateMoneyNote(note: MoneyNote): Boolean {
        val noteUpdate = MoneyNoteToNoteResponse().convert(note)
        database.collection(Const.NOTE).document(noteUpdate.id ?: "")
            .set(noteUpdate, SetOptions.merge()).await()
        return true
    }

    override suspend fun addMoneyNote(note: MoneyNote): Boolean {
        val noteAdd = MoneyNoteToNoteResponse().convert(note)
        val id = database.collection(Const.NOTE).add(noteAdd).await().id
        noteAdd.id = id
        database.collection(Const.NOTE).document(id).set(noteAdd, SetOptions.merge())
        return true

    }

    override suspend fun deleteMoneyNote(noteId: String): Boolean {
        database.collection(Const.NOTE).document(noteId).delete().await()
        return true
    }
}