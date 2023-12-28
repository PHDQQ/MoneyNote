package com.duongph.moneynote.presenter.addcategory

import android.view.View
import androidx.fragment.app.viewModels
import com.duongph.moneynote.presenter.base.BaseViewModel
import com.example.mynotehilt.databinding.FragmentAddCategoryBinding
import com.example.mynotehilt.ui.base.BaseFragment

class AddCategoryFragment : BaseFragment<FragmentAddCategoryBinding>() {
    private val viewModel: AddCategoryViewModel by viewModels()
    private var viewSelected: View? = null


    override fun initObserve() {
        with(viewModel) {
            stateAddCategoryLiveData.observe {
                if (it) {
                    showToast("Thêm thành công")
                    binding.edtNameCategory.setText("")
                }
            }
        }
    }

    override fun initView() {
        with(binding) {

            binding.tvHomeMoneyOut.performClick()
        }

    }

    override fun initListener() {
        with(binding) {
            btnSave.setOnClickListener {
                if (edtNameCategory.text.toString().isNullOrEmpty()) {
                    showToast("Vui lòng nhập tên danh mục")
                } else {

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