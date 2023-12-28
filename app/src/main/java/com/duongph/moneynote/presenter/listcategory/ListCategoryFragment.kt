package com.duongph.moneynote.presenter.listcategory

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.DividerItemDecoration
import com.duongph.moneynote.presenter.base.BaseViewModel
import com.example.mynotehilt.databinding.FragmentListCategoryBinding
import com.example.mynotehilt.ui.base.BaseFragment


class ListCategoryFragment : BaseFragment<FragmentListCategoryBinding>() {

    private val viewModel: ListCategoryViewModel by viewModels()
    private var viewSelected: View? = null


    override fun initObserve() {
        with(viewModel) {


            moneyState.asLiveData().observe {
                viewModel.updateListCategory()
            }
        }
    }

    override fun initView() {
        with(binding) {
            initRvCategory()
            tvHomeMoneyOut.performClick()
        }
    }

    override fun initListener() {
        with(binding) {
            tvHomeMoneyOut.setOnClickListener {
                setViewSelected(it)
                viewModel.setMoneyOut(true)
            }
            tvHomeMoneyIn.setOnClickListener {
                setViewSelected(it)
                viewModel.setMoneyOut(false)
            }

            ivRight.setOnClickListener {
//                findNavController().navigate(ListCategoryFragmentDirections.actionAddCategory(null))
            }
        }
    }

    private fun initRvCategory() {
        with(binding) {

            rvListCategory.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun setViewSelected(viewNew: View) {
        viewSelected?.isSelected = false
        viewNew.isSelected = true
        viewSelected = viewNew
    }

    override fun getViewModel(): BaseViewModel = viewModel
}