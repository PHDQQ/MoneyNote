package com.duongph.moneynote.presenter.listcategory

import androidx.lifecycle.viewModelScope
import com.duongph.moneynote.presenter.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListCategoryViewModel : BaseViewModel() {


    private val _moneyState = MutableStateFlow(true)
    var moneyState: StateFlow<Boolean> = _moneyState


    init {

    }

    private fun getCategory() {
        viewModelScope.launch {

        }
    }

    fun updateListCategory() {

    }

    fun setMoneyOut(isMoneyOut: Boolean) {
        _moneyState.value = isMoneyOut
    }

}
