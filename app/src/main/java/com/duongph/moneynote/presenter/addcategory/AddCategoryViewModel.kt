package com.duongph.moneynote.presenter.addcategory

import androidx.lifecycle.MutableLiveData
import com.duongph.moneynote.presenter.base.BaseViewModel

class AddCategoryViewModel : BaseViewModel() {
    private val _stateAddCategoryLiveData = MutableLiveData(false)
    var stateAddCategoryLiveData = _stateAddCategoryLiveData


}