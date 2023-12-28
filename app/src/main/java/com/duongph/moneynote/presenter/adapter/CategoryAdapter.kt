package com.duongph.moneynote.presenter.adapter

import android.graphics.Color
import android.view.View
import com.duongph.moneynote.domain.model.Category
import com.duongph.moneynote.getResourceId
import com.duongph.moneynote.gone
import com.duongph.moneynote.setBackgroundCompat
import com.duongph.moneynote.setTextColorCompat
import com.duongph.moneynote.show
import com.example.mynotehilt.R
import com.example.mynotehilt.databinding.ItemHomeCategoryBinding
import com.example.mynotehilt.ui.base.adapter.BaseRcvAdapter
import com.duongph.moneynote.presenter.base.adapter.BaseRcvVH
import com.example.mynotehilt.ui.base.adapter.BaseVHData

class CategoryAdapter : BaseRcvAdapter() {
    companion object {
        const val TYPE_ITEM = 0
        const val TYPE_ADD = 1
    }

    var onItemClick: ((Category) -> Unit)? = null
    var onAddItemClick: (() -> Unit)? = null


    fun setData(newItems: List<Category>, isShowButtonAdd: Boolean = false) {
        mDataSet.clear()
        mDataSetAllTemp.clear()
        newItems.map {
            mDataSet.add(CategoryVHData(it).apply {
                isSelected = mDataSet.size == 0
                type = TYPE_ITEM
                resourceId = it.resourceName?.getResourceId() ?: 0
                colorFilter = Color.parseColor(it.color)
            })
        }
        if (isShowButtonAdd) {
            mDataSet.add(CategoryVHData(Category()).apply {
                type = TYPE_ADD
            })
        }
        notifyDataSetChanged()
    }

    fun getItemSelected(): Category {
        return (mDataSet[getPositionSelected()] as CategoryVHData).realData!!
    }


    override fun getItemViewType(position: Int): Int = (mDataSet[position] as CategoryVHData).type

    override fun getLayoutResource(viewType: Int) = R.layout.item_home_category

    override fun onCreateVH(itemView: View, viewType: Int): BaseRcvVH<*> {
        return if (viewType == TYPE_ITEM) {
            CategoryItemVH(itemView)
        } else {
            AddCategoryItemVH(itemView)
        }
    }

    inner class CategoryItemVH(itemView: View) : BaseRcvVH<CategoryVHData>(itemView) {
        private val binding: ItemHomeCategoryBinding by lazy {
            ItemHomeCategoryBinding.bind(itemView)
        }

        init {
            itemView.setOnClickListener {
                if (adapterPosition > -1) {
                    onOneChoice(adapterPosition)
                }
            }
            with(binding) {
                ivCategoryIcon.show()
                tvCategoryName.show()
                tvCategoryAdd.gone()
            }
        }

        override fun onBind(data: CategoryVHData) {
            with(binding) {
                ivCategoryIcon.setImageResource(data.resourceId)
                ivCategoryIcon.setColorFilter(data.colorFilter)
                tvCategoryName.text = data.realData?.name
                if (data.isSelected) {
                    llItemHomeCategory.setBackgroundCompat(R.drawable.bg_text_un_selected)
                    tvCategoryName.setTextColorCompat(R.color.color_FFEC407A)
                } else {
                    llItemHomeCategory.setBackgroundCompat(R.drawable.bg_text_gray)
                    tvCategoryName.setTextColorCompat(R.color.black)
                }
            }
        }
    }

    inner class AddCategoryItemVH(itemView: View) : BaseRcvVH<CategoryVHData>(itemView) {
        private val binding: ItemHomeCategoryBinding by lazy {
            ItemHomeCategoryBinding.bind(itemView)
        }

        init {
            itemView.setOnClickListener {
                if (adapterPosition > -1) {
                    onAddItemClick?.invoke()
                }
            }
            with(binding) {
                ivCategoryIcon.gone()
                tvCategoryName.gone()
                tvCategoryAdd.show()
                ivCategoryIcon.colorFilter = null
                llItemHomeCategory.setBackgroundCompat(R.drawable.bg_text_gray)
                tvCategoryName.setTextColorCompat(R.color.black)
            }
        }

        override fun onBind(data: CategoryVHData) {
        }
    }


    inner class CategoryVHData(data: Category) : BaseVHData<Category>(data) {
        var resourceId: Int = 0
        var colorFilter: Int = 0
    }

}