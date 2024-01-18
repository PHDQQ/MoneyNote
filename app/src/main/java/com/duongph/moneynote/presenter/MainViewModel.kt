package com.duongph.moneynote.presenter

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.duongph.moneynote.action.BaseAction
import com.duongph.moneynote.domain.action.sync.SyncDataAction
import com.duongph.moneynote.presenter.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel : BaseViewModel() {
    private val syncDataAction = SyncDataAction()

    init {
//        viewModelScope.launch(Dispatchers.Main) {
//            showLoading()
//            syncDataAction.invoke(BaseAction.VoidRequest()).collect {
//                hideLoading()
//                Log.d("duongph", "syncDataAction: done")
//            }
//        }
    }
}