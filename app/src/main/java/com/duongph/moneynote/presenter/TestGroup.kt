package com.duongph.moneynote.presenter

import android.view.View
import com.duongph.moneynote.presenter.base.groupdata.GroupData
import com.duongph.moneynote.presenter.base.groupdata.GroupVH
import com.duongph.moneynote.presenter.base.adapter.BaseRcvVH

class TestGroup : GroupData<String>("") {
    override fun getItemViewType(position: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getDataInGroup(positionInGroup: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getLayoutResource(viewType: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getCount() = 1

    override fun onCreateVH(itemView: View, viewType: Int): BaseRcvVH<*> {
        return TitleVH(itemView)
    }

    class TitleVH(itemView: View) : GroupVH<String, TestGroup>(itemView) {


        init {

        }

        override fun onBind(data: String) {
            var displayTime = ""
        }
    }
}