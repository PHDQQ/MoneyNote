package com.duongph.moneynote.presenter.notifications

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.duongph.moneynote.domain.action.category.GetCategoryWithNoteAction
import com.duongph.moneynote.domain.action.note.GetNoteInfoAction
import com.duongph.moneynote.domain.model.ChooseTimeMode
import com.duongph.moneynote.domain.model.TYPE_MONEY
import com.duongph.moneynote.getCurrentMonth
import com.duongph.moneynote.getCurrentYear
import com.duongph.moneynote.presenter.base.BaseViewModel
import com.duongph.moneynote.presenter.model.MoneyNotePage
import com.duongph.moneynote.toJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.util.Calendar

class NotificationsViewModel : BaseViewModel() {

    private val getNoteInfoAction = GetNoteInfoAction()
    private val getCategoryWithNoteAction = GetCategoryWithNoteAction()

    private val _timeLiveData = MutableLiveData<String>().apply {
        value = Calendar.getInstance().time.getCurrentMonth()
    }
    val timeLiveData: LiveData<String> = _timeLiveData

    private val _yearLiveData = MutableLiveData<String>().apply {
        value = Calendar.getInstance().time.getCurrentYear()
    }
    val yearLiveData: LiveData<String> = _yearLiveData

    private val _moneyNotePageLiveData = MutableLiveData<MoneyNotePage>()
    val moneyNotePageLiveData: LiveData<MoneyNotePage> = _moneyNotePageLiveData

    private var monthCalendar = Calendar.getInstance()
    private var yearCalendar = Calendar.getInstance()
    private var chooseMode: ChooseTimeMode = ChooseTimeMode.MONTH

    init {
        viewModelScope.launch(Dispatchers.Main) {
            getCategoryWithNoteAction.invoke(GetCategoryWithNoteAction.GetCategoryWithNoteActionRV().apply {
                typeMoney = TYPE_MONEY.MONEY_OUT
                Log.d("duongph", "monthCalendar: "+monthCalendar.timeInMillis)
                timeStamp = monthCalendar.timeInMillis
            }).collect{
                Log.d("duongph", "done: "+it.toJson())
            }
        }

    }

    fun setChooseTimeMode(mode: ChooseTimeMode) {
        chooseMode = mode
        if (chooseMode == ChooseTimeMode.MONTH) {
            _timeLiveData.value = monthCalendar.time.getCurrentMonth()
        } else {
            _timeLiveData.value = yearCalendar.time.getCurrentYear()
        }
        updateData()
    }

    fun nextTime() {
        _timeLiveData.value = if (chooseMode == ChooseTimeMode.MONTH) {
            monthCalendar.add(Calendar.MONTH, 1)
            monthCalendar.time.getCurrentMonth()
        } else {
            yearCalendar.add(Calendar.YEAR, 1)
            yearCalendar.time.getCurrentYear()
        }

        updateData()
    }

    fun preTime() {
        if (chooseMode == ChooseTimeMode.MONTH) {
            monthCalendar.add(Calendar.MONTH, -1)
            _timeLiveData.value = monthCalendar.time.getCurrentMonth()
        } else {
            yearCalendar.add(Calendar.YEAR, -1)
            _timeLiveData.value = yearCalendar.time.getCurrentYear()
        }
        updateData()
    }

    private fun updateData() = viewModelScope.launch(Dispatchers.Main) {
        getNoteInfoAction.invoke(GetNoteInfoAction.GetNoteInfoRV().apply {
            month = _timeLiveData.value!!
        }).catch {
            it.printStackTrace()
        }.collect {
            val page = MoneyNotePage().apply {
                addNewListByCategoryNote(it.listMoneyOut)
            }

            _moneyNotePageLiveData.postValue(page)
        }

    }

}