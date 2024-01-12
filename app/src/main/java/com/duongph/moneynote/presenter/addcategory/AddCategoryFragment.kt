package com.duongph.moneynote.presenter.addcategory

import android.view.View
import androidx.fragment.app.viewModels
import com.duongph.moneynote.domain.model.Category
import com.duongph.moneynote.domain.model.TYPE_MONEY
import com.duongph.moneynote.presenter.adapter.ColorAdapter
import com.duongph.moneynote.presenter.adapter.IconAdapter
import com.duongph.moneynote.presenter.base.BaseFragment
import com.duongph.moneynote.presenter.base.BaseViewModel
import com.example.mynotehilt.databinding.FragmentAddCategoryBinding

class AddCategoryFragment : BaseFragment<FragmentAddCategoryBinding>() {
    private val viewModel: AddCategoryViewModel by viewModels()
    private var viewSelected: View? = null
    private val colorAdapter = ColorAdapter()
    private val iconAdapter = IconAdapter()
    private var categoryDto: Category? = null

    override fun initObserve() {
        with(viewModel) {
            addCategoryMutableLiveData.observe {
                if (it) {
                    showToast("Thêm thành công")
                    binding.edtNameCategory.setText("")
                }
            }
        }
    }

    override fun initView() {
        with(binding) {
            iconAdapter.reset(viewModel.getListIcon() as ArrayList<Int>)
            rvIcon.adapter = iconAdapter

            colorAdapter.reset(viewModel.getListColor() as ArrayList<String>)
            colorAdapter.onItemClick = {
                iconAdapter.colorSelected = colorAdapter.getItemSelected()
            }
            rvColors.adapter = colorAdapter
//            if (arguments?.getParcelable<Category>("category") != null) {
//                categoryDto = arguments?.getParcelable<Category>("category")
//                edtNameCategory.setText(categoryDto?.name)
//            }
            binding.tvHomeMoneyOut.performClick()
        }

    }

    override fun initListener() {
        with(binding) {
            btnSave.setOnClickListener {
                if (edtNameCategory.text.toString().isNullOrEmpty()) {
                    showToast("Vui lòng nhập tên danh mục")
                } else {
                    viewModel.addCategory(
                        Category(
                            name = edtNameCategory.text.toString(),
                            color = colorAdapter.getItemSelected(),
                            resourceName = iconAdapter.getItemSelected(),
                            typeMoney = if (viewSelected == tvHomeMoneyOut) {
                                TYPE_MONEY.MONEY_OUT
                            }else {
                                TYPE_MONEY.MONEY_IN
                            }
                        )
                    )
                }
            }

            tvHomeMoneyOut.setOnClickListener {
                setViewSelected(it)
            }
            tvHomeMoneyIn.setOnClickListener {
                setViewSelected(it)
            }
        }
    }

    private fun setViewSelected(viewNew: View) {
        viewSelected?.isSelected = false
        viewNew.isSelected = true
        viewSelected = viewNew
    }

    override fun getViewModel(): BaseViewModel = viewModel

}