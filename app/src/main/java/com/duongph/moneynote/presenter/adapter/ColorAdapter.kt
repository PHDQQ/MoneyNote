package com.duongph.moneynote.presenter.adapter

import android.view.View
import com.duongph.moneynote.presenter.base.adapter.BaseRcvVH
import com.duongph.moneynote.setBackgroundColorCompat
import com.duongph.moneynote.setBackgroundCompat
import com.example.mynotehilt.R
import com.example.mynotehilt.databinding.ItemColorBinding
import com.example.mynotehilt.ui.base.adapter.BaseRcvAdapter
import com.example.mynotehilt.ui.base.adapter.BaseVHData

class ColorAdapter: BaseRcvAdapter() {
    var onItemClick: ((Int) -> Unit)? = null

    override fun getLayoutResource(viewType: Int) = R.layout.item_color

    override fun onCreateVH(itemView: View, viewType: Int): BaseRcvVH<*> {
        return ColorViewHolder(itemView)
    }

    override fun reset(newItems: List<*>?) {
        mDataSet.clear()
        mDataSetAllTemp.clear()
        newItems!!.map {
            mDataSet.add(ColorData(it as String).apply {
                isSelected = mDataSet.size == 0
            })
        }
        notifyDataSetChanged()
    }

    fun getItemSelected(): String {
        return (mDataSet[getPositionSelected()] as ColorData).realData.toString()
    }

    inner class ColorViewHolder(itemView: View) : BaseRcvVH<ColorData>(itemView) {
        private val binding: ItemColorBinding by lazy { ItemColorBinding.bind(itemView) }

        init {
            itemView.setOnClickListener {
                if (adapterPosition > -1) {
                    onOneChoice(adapterPosition)
                    onItemClick?.invoke(adapterPosition)
                }
            }
        }

        override fun onBind(data: ColorData) {
            binding.vColor.setBackgroundColorCompat(data.realData!!)
            if (data.isSelected) {
                binding.llItemColor.setBackgroundCompat(R.drawable.bg_text_un_selected)
            } else {
                binding.llItemColor.background = null
            }
        }
    }

    inner class ColorData(data: String) : BaseVHData<String>(data)

}