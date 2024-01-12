package com.duongph.moneynote.presenter.adapter

import android.graphics.Color
import android.view.View
import com.duongph.moneynote.common.Const
import com.duongph.moneynote.presenter.base.adapter.BaseRcvVH
import com.duongph.moneynote.setBackgroundCompat
import com.example.mynotehilt.R
import com.example.mynotehilt.databinding.ItemIconBinding
import com.example.mynotehilt.ui.base.adapter.BaseRcvAdapter
import com.example.mynotehilt.ui.base.adapter.BaseVHData

class IconAdapter : BaseRcvAdapter() {
    var onItemClick: ((Int) -> Unit)? = null

    var colorSelected: String = Const.COLORS[0]
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getLayoutResource(viewType: Int) = R.layout.item_icon
    override fun onCreateVH(itemView: View, viewType: Int): BaseRcvVH<*> {
        return IconViewHolder(itemView)
    }

    override fun reset(newItems: List<*>?) {
        mDataSet.clear()
        mDataSetAllTemp.clear()
        newItems!!.map {
            mDataSet.add(IconData(it as Int).apply {
                isSelected = mDataSet.size == 0
            })
        }
        notifyDataSetChanged()
    }


    fun getItemSelected(): String {
        return (mDataSet[getPositionSelected()] as IconData).realData.toString()
    }

    inner class IconViewHolder(itemView: View) : BaseRcvVH<IconData>(itemView) {
        private val binding: ItemIconBinding by lazy { ItemIconBinding.bind(itemView) }

        init {
            itemView.setOnClickListener {
                if (adapterPosition > -1) {
                    onOneChoice(adapterPosition)
                    onItemClick?.invoke(adapterPosition)
                }
            }
        }

        override fun onBind(data: IconData) {
            binding.ivIcon.setImageResource(data.realData!!)
            if (data.isSelected) {
                binding.ivIcon.setColorFilter(Color.parseColor(colorSelected))
                binding.llItemIcon.setBackgroundCompat(R.drawable.bg_text_un_selected)
            } else {
                binding.ivIcon.colorFilter = null
                binding.llItemIcon.background = null
            }
        }
    }

    inner class IconData(data: Int) : BaseVHData<Int>(data)
}