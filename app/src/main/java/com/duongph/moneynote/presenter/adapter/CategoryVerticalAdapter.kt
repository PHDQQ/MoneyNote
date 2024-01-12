package com.duongph.moneynote.presenter.adapter

import android.graphics.Color
import android.view.View
import com.duongph.moneynote.domain.model.Category
import com.duongph.moneynote.getResourceId
import com.duongph.moneynote.presenter.base.adapter.BaseRcvVH
import com.duongph.moneynote.setTextColorCompat
import com.example.mynotehilt.R
import com.example.mynotehilt.databinding.ItemCategoryVerticalBinding
import com.example.mynotehilt.ui.base.adapter.BaseRcvAdapter
import com.example.mynotehilt.ui.base.adapter.BaseVHData

class CategoryVerticalAdapter: BaseRcvAdapter()  {

    fun setData(newItems: List<Category>) {
        mDataSet.clear()
        mDataSetAllTemp.clear()
        newItems.map {
            mDataSet.add(CategoryVHData(it).apply {
                isSelected = mDataSet.size == 0
                type = CategoryAdapter.TYPE_ITEM
                resourceId = it.resourceName?.getResourceId() ?: 0
                colorFilter = Color.parseColor(it.color)
            })
        }
        notifyDataSetChanged()
    }

    override fun getLayoutResource(viewType: Int): Int {
        return R.layout.item_category_vertical
    }

    override fun onCreateVH(itemView: View, viewType: Int): BaseRcvVH<*> {
        return CategoryVH(itemView)
    }

    inner class CategoryVH(itemView: View) : BaseRcvVH<CategoryVHData>(itemView) {
        private val binding: ItemCategoryVerticalBinding by lazy {
            ItemCategoryVerticalBinding.bind(itemView)
        }

        override fun onBind(data: CategoryVHData) {
            with(binding) {
                ivCategoryIcon.setImageResource(data.resourceId)
                ivCategoryIcon.setColorFilter(data.colorFilter)
                tvCategoryName.text = data.realData?.name
                if (data.isSelected) {
                    tvCategoryName.setTextColorCompat(R.color.color_FFEC407A)
                } else {
                    tvCategoryName.setTextColorCompat(R.color.black)
                }
            }
        }

    }

    class CategoryVHData(data: Category) : BaseVHData<Category>(data) {
        var resourceId: Int = 0
        var colorFilter: Int = 0
    }
}