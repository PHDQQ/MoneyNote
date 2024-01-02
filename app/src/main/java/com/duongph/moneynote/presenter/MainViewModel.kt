package com.duongph.moneynote.presenter

import androidx.lifecycle.viewModelScope
import com.duongph.moneynote.action.BaseAction
import com.duongph.moneynote.domain.action.sync.SyncDataAction
import com.duongph.moneynote.presenter.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MainViewModel : BaseViewModel() {
    private val syncDataAction = SyncDataAction()

    init {
        viewModelScope.launch(Dispatchers.Main) {
            syncDataAction.invoke(BaseAction.VoidRequest()).catch {
                it.printStackTrace()
            }.collect()
        }
    }
}