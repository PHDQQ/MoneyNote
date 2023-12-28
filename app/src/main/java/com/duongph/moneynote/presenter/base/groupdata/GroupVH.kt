package com.duongph.moneynote.presenter.base.groupdata

import android.view.View
import com.duongph.moneynote.presenter.base.adapter.BaseRcvVH


abstract class GroupVH<T, GD : GroupData<*>>(itemView: View) :
    BaseRcvVH<T>(itemView) {

    var groupData: GD? = null

}