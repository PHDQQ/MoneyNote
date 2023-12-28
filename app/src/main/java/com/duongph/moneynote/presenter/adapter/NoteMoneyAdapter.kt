package com.duongph.moneynote.presenter.adapter

import android.graphics.Color
import android.view.View
import com.duongph.moneynote.domain.model.MoneyNote
import com.duongph.moneynote.getResourceId
import com.duongph.moneynote.presenter.base.adapter.BaseRcvVH
import com.example.mynotehilt.R
import com.example.mynotehilt.databinding.ItemMoneyNoteBinding
import com.example.mynotehilt.ui.base.adapter.BaseRcvAdapter
import com.example.mynotehilt.ui.base.adapter.BaseVHData

class NoteMoneyAdapter : BaseRcvAdapter() {

    fun setData(newItems: List<MoneyNote>) {
        newItems.forEach {
            mDataSet.add(MoneyNoteVHData(it).apply {
                iconId = it.category!!.resourceName!!.getResourceId()
                colorIconFilter = it.category!!.color ?: ""
            })
        }
    }

    override fun getLayoutResource(viewType: Int): Int {
        return R.layout.item_money_note
    }

    override fun onCreateVH(itemView: View, viewType: Int): BaseRcvVH<*> {
        return MoneyNoteVH(itemView)
    }

    inner class MoneyNoteVH(itemView: View) : BaseRcvVH<MoneyNoteVHData>(itemView) {
        private val binding: ItemMoneyNoteBinding by lazy {
            ItemMoneyNoteBinding.bind(itemView)
        }

        override fun onBind(data: MoneyNoteVHData) {
            with(binding) {
                ivNoteIcon.setImageResource(data.iconId)
                ivNoteIcon.setColorFilter(Color.parseColor(data.colorIconFilter))
                sbMoney.progress = data.seekBarProgress
            }
        }

    }

    class MoneyNoteVHData(data: MoneyNote) : BaseVHData<MoneyNote>(data) {
        var iconId = 0
        var colorIconFilter = ""
        var seekBarProgress = 20
    }
}