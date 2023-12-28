package com.duongph.moneynote.presenter.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.duongph.moneynote.domain.action.note.DeleteNoteAction
import com.duongph.moneynote.domain.action.note.GetNoteInfoAction
import com.duongph.moneynote.domain.model.MoneyNote
import com.duongph.moneynote.getCurrentMonth
import com.duongph.moneynote.presenter.base.BaseViewModel
import com.duongph.moneynote.presenter.model.MoneyNotePage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.util.Calendar

class DashboardViewModel : BaseViewModel() {
    private val getNoteInfoAction = GetNoteInfoAction()
    private val deleteNoteInfoAction = DeleteNoteAction()
    private val _monthLiveData = MutableLiveData<String>().apply {
        value = Calendar.getInstance().time.getCurrentMonth()
    }
    val monthLiveData: LiveData<String> = _monthLiveData
    private val _moneyNotePageLiveData = MutableLiveData<MoneyNotePage>()
    val moneyNotePageLiveData: LiveData<MoneyNotePage> = _moneyNotePageLiveData

    private val _delNoteState = MutableLiveData<Boolean>()
    var delNoteState: LiveData<Boolean> = _delNoteState

    private var calendar = Calendar.getInstance()

    init {
        updateData()
    }

    private fun updateData() = viewModelScope.launch(Dispatchers.IO) {
        getNoteInfoAction.invoke(GetNoteInfoAction.GetNoteInfoRV().apply {
            month = _monthLiveData.value!!
        }).catch {
            it.printStackTrace()
        }.collect {
            val page = MoneyNotePage().apply {
                addNewListMoneyNote(it.listMoneyOut)
                moneyIn = it.moneyIn
                moneyOut = it.moneyOut
            }

            _moneyNotePageLiveData.postValue(page)
        }

    }

    fun nextMonth() {
        calendar.add(Calendar.MONTH, 1)
        _monthLiveData.value = calendar.time.getCurrentMonth()
        updateData()
    }

    fun preMonth() {
        calendar.add(Calendar.MONTH, -1)
        _monthLiveData.value = calendar.time.getCurrentMonth()
        updateData()
    }

    fun deleteNote(moneyNote: MoneyNote) = viewModelScope.launch(Dispatchers.IO) {
        deleteNoteInfoAction.invoke(DeleteNoteAction.DeleteNoteRV().apply {
            noteId = moneyNote.id.toString()
        }).catch {
            it.printStackTrace()
        }.collect {
            _delNoteState.postValue(it)
        }
    }
}
