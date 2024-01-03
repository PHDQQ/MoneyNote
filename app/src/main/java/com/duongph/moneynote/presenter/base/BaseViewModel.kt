package com.duongph.moneynote.presenter.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch


abstract class BaseViewModel : ViewModel() {
    //    private val initialState: State by lazy { createInitialState() }
//    abstract fun createInitialState(): State
//    abstract fun onTriggerEvent(event: Event)
//
    protected var _uiState: MutableLiveData<Boolean> = MutableLiveData(false)
    var uiState: LiveData<Boolean> = _uiState
    protected suspend fun <T> call(
        callFlow: Flow<T>,
        completionHandler: (collect: T) -> Unit = {}
    ) {
        callFlow
            .catch { }
            .collect {
                completionHandler.invoke(it)
            }
    }

    fun showLoading() {
        _uiState.postValue(true)
    }

    fun hideLoading() {
        _uiState.postValue(false)
    }
}