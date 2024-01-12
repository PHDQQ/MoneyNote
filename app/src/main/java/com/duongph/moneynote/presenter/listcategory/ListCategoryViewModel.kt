package com.duongph.moneynote.presenter.listcategory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.duongph.moneynote.domain.action.category.GetCategoryAction
import com.duongph.moneynote.domain.model.Category
import com.duongph.moneynote.domain.model.TYPE_MONEY
import com.duongph.moneynote.presenter.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ListCategoryViewModel : BaseViewModel() {
    private val categoryAction = GetCategoryAction()

    private val _categoryLiveData = MutableLiveData<List<Category>>()
    var categoryLiveData: LiveData<List<Category>> = _categoryLiveData

    private var moneyType = TYPE_MONEY.MONEY_OUT

    init {
        getCategory()
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

    fun setMoneyOut(moneyType: TYPE_MONEY) {
        this.moneyType = moneyType
        getCategory()
    }

}
