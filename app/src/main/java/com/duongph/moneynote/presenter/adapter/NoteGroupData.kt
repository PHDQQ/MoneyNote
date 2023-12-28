package com.duongph.moneynote.presenter.adapter

import android.graphics.Color
import android.util.Log
import android.view.View
import com.duongph.moneynote.domain.model.MoneyNote
import com.duongph.moneynote.domain.model.TYPE_MONEY
import com.duongph.moneynote.getMoney
import com.duongph.moneynote.getResourceId
import com.duongph.moneynote.presenter.base.adapter.BaseRcvVH
import com.duongph.moneynote.presenter.base.groupdata.GroupData
import com.duongph.moneynote.presenter.base.groupdata.GroupVH
import com.duongph.moneynote.presenter.model.MoneyGroup
import com.duongph.moneynote.setTextColorCompat
import com.duongph.moneynote.toJson
import com.example.mynotehilt.R
import com.example.mynotehilt.databinding.ItemNoteBinding
import com.example.mynotehilt.databinding.ItemNoteDateBinding

class NoteGroupData : GroupData<MutableList<MoneyNote>>(mutableListOf()) {
    companion object {
        val TITLE_VIEW_TYPE = 0
        val NOTE_VIEW_TYPE = 1
    }

    var groupTime: String = ""
    var groupMoney: String = ""
    var onClickItem: ((MoneyNote) -> Unit)? = null

    fun resetMoneyGroup(moneyGroup: MoneyGroup) {
        this.groupTime = moneyGroup.groupTime
        this.groupMoney = moneyGroup.groupMoney
        this.data.clear()
        this.data.addAll(moneyGroup.dataList)
    }

    fun removeItem(positionInGroup: Int) {
        this.data.removeAt(positionInGroup - 1)
        notifyRemove(positionInGroup)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TITLE_VIEW_TYPE
        } else {
            NOTE_VIEW_TYPE
        }
    }

    override fun getDataInGroup(positionInGroup: Int): Any {
        if (positionInGroup == 0) return ""
        return data[positionInGroup - 1]
    }

    override fun getLayoutResource(viewType: Int): Int {
        return when (viewType) {
            TITLE_VIEW_TYPE -> {
                R.layout.item_note_date
            }

            else -> {
                R.layout.item_note
            }
        }
    }

    override fun getCount(): Int {
        return data.size + 1
    }

    override fun onCreateVH(itemView: View, viewType: Int): BaseRcvVH<*>? {
        return when (viewType) {
            TITLE_VIEW_TYPE -> {
                DateVH(itemView)
            }

            else -> {
                NoteVH(itemView)
            }
        }
    }

    class NoteVH(itemView: View) : GroupVH<MoneyNote, NoteGroupData>(itemView) {
        private val binding = ItemNoteBinding.bind(itemView)

        init {
            itemView.setOnClickListener {

                try {
                    val p = groupData!!.mapAdapterPositionToGroupPosition(adapterPosition)
                    groupData!!.onClickItem?.invoke(groupData!!.data[p - 1])
                } catch (e: Exception) {
                    Log.d("duongph", "Exception: " + e.toJson())
                }
            }
        }

        override fun onBind(data: MoneyNote) {
            with(binding) {
                tvNoteName.text = data.category?.name
                tvNoteDes.text = data.note ?: ""
                data.category?.let {
                    ivNoteIcon.setImageResource(it.resourceName!!.getResourceId())
                    ivNoteIcon.setColorFilter(Color.parseColor(it.color ?: ""))
                }
                if (data.typeMoney == TYPE_MONEY.MONEY_IN) {
                    tvNoteMoney.text = ("" + data.money?.getMoney())
                    tvNoteMoney.setTextColorCompat(R.color.teal_00E676)
                }else {
                    tvNoteMoney.text = ("-" + data.money?.getMoney())
                    tvNoteMoney.setTextColorCompat(R.color.color_F50057)
                }

            }
        }

    }


    class DateVH(itemView: View) : GroupVH<String, NoteGroupData>(itemView) {
        private val binding = ItemNoteDateBinding.bind(itemView)

        override fun onBind(data: String) {
            with(binding) {
                tvDateTitle.text = groupData!!.groupTime
                tvDateMoney.text = groupData!!.groupMoney
            }
        }

    }
}