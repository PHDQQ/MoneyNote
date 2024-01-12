package com.duongph.moneynote.presenter.listcategory

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.duongph.moneynote.domain.model.TYPE_MONEY
import com.duongph.moneynote.presenter.adapter.CategoryVerticalAdapter
import com.duongph.moneynote.presenter.base.BaseFragment
import com.duongph.moneynote.presenter.base.BaseViewModel
import com.example.mynotehilt.databinding.FragmentListCategoryBinding


class ListCategoryFragment : BaseFragment<FragmentListCategoryBinding>() {

    private val viewModel: ListCategoryViewModel by viewModels()
    private var viewSelected: View? = null

    private val categoryVerticalAdapter = CategoryVerticalAdapter()
    override fun initObserve() {
        with(viewModel) {
            categoryLiveData.observe {
                categoryVerticalAdapter.setData(it)
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
                viewModel.setMoneyOut(TYPE_MONEY.MONEY_OUT)
            }
            tvHomeMoneyIn.setOnClickListener {
                setViewSelected(it)
                viewModel.setMoneyOut(TYPE_MONEY.MONEY_IN)
            }

            ivRight.setOnClickListener {
                findNavController().navigate(ListCategoryFragmentDirections.actionAddCategory())
            }
        }
    }

    private fun initRvCategory() {
        with(binding) {
            rvListCategory.adapter = categoryVerticalAdapter
            rvListCategory.addItemDecoration(
                DividerItemDecoration(
                    context, DividerItemDecoration.VERTICAL
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