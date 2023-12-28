package com.duongph.moneynote.presenter.editnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.duongph.moneynote.domain.action.category.GetCategoryAction
import com.duongph.moneynote.domain.action.note.UpdateNoteAction
import com.duongph.moneynote.domain.model.Category
import com.duongph.moneynote.domain.model.MoneyNote
import com.duongph.moneynote.domain.model.TYPE_MONEY
import com.duongph.moneynote.getNameDayOfWeek
import com.duongph.moneynote.getToDay
import com.duongph.moneynote.presenter.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.util.Calendar

class EditNoteViewModel : BaseViewModel() {
    private val categoryAction = GetCategoryAction()
    private val updateNoteAction = UpdateNoteAction()

    private val _dateLiveData = MutableLiveData<String>().apply {
        value = ""
    }
    val dateLiveData: LiveData<String> = _dateLiveData

    private val _categoryLiveData = MutableLiveData<List<Category>>()
    var categoryLiveData: LiveData<List<Category>> = _categoryLiveData

    private val _moneyLiveData = MutableLiveData(true)
    var moneyLiveData: MutableLiveData<Boolean> = _moneyLiveData

    private var calendar = Calendar.getInstance()
    private var _editNoteLiveData = MutableLiveData(false)
    val editNoteLiveData: LiveData<Boolean> = _editNoteLiveData

    var moneyNoteSelected: MoneyNote? = null

    fun getCategory() {
        viewModelScope.launch(Dispatchers.Main) {
            categoryAction.invoke(GetCategoryAction.GetCategoryActionRV().apply {
                this.typeMoney = TYPE_MONEY.MONEY_OUT
            }).catch {
                it.printStackTrace()
            }.collect {
                _categoryLiveData.postValue(it)
            }
        }
    }

    fun nextDay() {
        calendar.add(Calendar.DAY_OF_WEEK, 1)
        _dateLiveData.postValue("${calendar.time.getToDay()} (${calendar.getNameDayOfWeek()})")
    }

    fun preDay() {
        calendar.add(Calendar.DAY_OF_WEEK, -1)
        _dateLiveData.postValue("${calendar.time.getToDay()} (${calendar.getNameDayOfWeek()})")
    }

    fun setDate(c: Calendar) {
        calendar = c
        _dateLiveData.postValue("${calendar.time.getToDay()} (${calendar.getNameDayOfWeek()})")
    }

    fun getDateString(): String {
        return calendar.time.getToDay()
    }

    fun getDateLong(): Long {
        return calendar.timeInMillis
    }

    fun saveNote(note: MoneyNote) {
        viewModelScope.launch(Dispatchers.Main) {
            updateNoteAction.invoke(UpdateNoteAction.UpdateNoteActionRV().apply {
                moneyNote = note
            }).catch {
                it.printStackTrace()
            }.collect {
                _editNoteLiveData.postValue(it)
            }
        }
    }
}