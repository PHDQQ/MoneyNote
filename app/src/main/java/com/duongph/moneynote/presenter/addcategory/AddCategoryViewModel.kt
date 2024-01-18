package com.duongph.moneynote.presenter.addcategory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.duongph.moneynote.common.Const
import com.duongph.moneynote.domain.action.category.AddCategoryAction
import com.duongph.moneynote.domain.model.Category
import com.duongph.moneynote.domain.model.IconModel
import com.duongph.moneynote.presenter.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class AddCategoryViewModel : BaseViewModel() {
    private val addCategoryAction = AddCategoryAction()
    private val _addCategoryMutableLiveData = MutableLiveData(false)
    var addCategoryMutableLiveData: LiveData<Boolean> = _addCategoryMutableLiveData


    fun getListIcon(): List<IconModel> {
        return Const.getCategoryImages()
    }

    fun getListColor(): List<String> {
        return Const.COLORS.toList()
    }

    fun addCategory(category: Category) = viewModelScope.launch(Dispatchers.Main) {
        addCategoryAction.invoke(AddCategoryAction.AddCategoryRV().apply {
            listCategory.add(category)
        }).catch {
            it.printStackTrace()
        }.collect {
            _addCategoryMutableLiveData.postValue(it)
        }
    }
}