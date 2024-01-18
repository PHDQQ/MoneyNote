package com.duongph.moneynote.presenter.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.duongph.moneynote.action.BaseAction
import com.duongph.moneynote.common.Event
import com.duongph.moneynote.domain.action.category.GetCategoryAction
import com.duongph.moneynote.domain.action.category.GetCategoryWithNoteAction
import com.duongph.moneynote.domain.action.note.AddNoteAction
import com.duongph.moneynote.domain.action.sync.SyncDataAction
import com.duongph.moneynote.domain.model.Category
import com.duongph.moneynote.domain.model.MoneyNote
import com.duongph.moneynote.domain.model.TYPE_MONEY
import com.duongph.moneynote.getNameDayOfWeek
import com.duongph.moneynote.getToDay
import com.duongph.moneynote.presenter.base.BaseViewModel
import com.duongph.moneynote.toJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel : BaseViewModel() {
    private val categoryAction = GetCategoryAction()
    private val addNoteUseCase = AddNoteAction()
    private val syncDataAction = SyncDataAction()

    private val _dateLiveData = MutableLiveData<String>().apply {
        value = ""
    }
    val dateLiveData: LiveData<String> = _dateLiveData

    private val _moneyLiveData = MutableLiveData(true)
    var moneyLiveData: MutableLiveData<Boolean> = _moneyLiveData

    private val _addNoteState = MutableLiveData<Event<Boolean>>()
    var addNoteState: LiveData<Event<Boolean>> = _addNoteState

    private val _categoryLiveData = MutableLiveData<List<Category>>()
    var categoryLiveData: LiveData<List<Category>> = _categoryLiveData


    private var calendar = Calendar.getInstance()
    private var moneyType = TYPE_MONEY.MONEY_OUT

    init {
        _dateLiveData.postValue("${calendar.time.getToDay()} (${calendar.getNameDayOfWeek()})")
        viewModelScope.launch(Dispatchers.Main) {
            showLoading()
            syncDataAction.invoke(BaseAction.VoidRequest()).collect {
                hideLoading()
                getCategory()
            }
        }
    }

    fun getAllNote() {
        viewModelScope.launch {

        }
    }

    fun getCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            categoryAction.invoke(GetCategoryAction.GetCategoryActionRV().apply {
                this.typeMoney = moneyType
            }).catch {
                it.printStackTrace()
            }.collect {
                _categoryLiveData.postValue(it)
            }
        }
    }

    fun addCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            categoryAction.invoke(GetCategoryAction.GetCategoryActionRV().apply {
                this.typeMoney = moneyType
            }).catch {
                it.printStackTrace()
            }.collect {
                Log.i("duongph", "getCategory: " + it.toJson())
                _categoryLiveData.postValue(it)
            }
        }
    }

    fun addNote(note: MoneyNote) = viewModelScope.launch(Dispatchers.IO) {
        addNoteUseCase.invoke(AddNoteAction.AddNoteActionRV().apply {
            moneyNote = note
        }).catch {
            it.printStackTrace()
        }.collect {
            _addNoteState.postValue(Event(it))
        }
    }

    fun setMoneyOut(isMoneyOut: Boolean) {
        moneyType = if (isMoneyOut) {
            TYPE_MONEY.MONEY_OUT
        } else {
            TYPE_MONEY.MONEY_IN
        }
        _moneyLiveData.value = isMoneyOut
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

    fun getDateLong(): Long {
        return calendar.timeInMillis
    }

}