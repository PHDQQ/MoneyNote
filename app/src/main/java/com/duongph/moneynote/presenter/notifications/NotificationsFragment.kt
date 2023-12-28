package com.duongph.moneynote.presenter.notifications

import android.view.View
import androidx.fragment.app.viewModels
import com.duongph.moneynote.domain.model.ChooseTimeMode
import com.duongph.moneynote.presenter.adapter.NoteMoneyAdapter
import com.duongph.moneynote.presenter.base.BaseViewModel
import com.example.mynotehilt.databinding.FragmentNotificationsBinding
import com.example.mynotehilt.ui.base.BaseFragment

class NotificationsFragment : BaseFragment<FragmentNotificationsBinding>() {
    private val viewModel: NotificationsViewModel by viewModels()

    private var viewSelected: View? = null

    private val noteAdapter = NoteMoneyAdapter()

    override fun initObserve() {
        with(viewModel) {
            timeLiveData.observe {
                binding.textCurrentMonth.text = it
            }
        }
    }

    override fun initView() {

        initRcvCategory()
        binding.tvNotificationByMonth.performClick()
    }

    override fun initListener() {
        with(binding) {
            ivPrevMonth.setOnClickListener {
                viewModel.preTime()
            }
            ivNextMonth.setOnClickListener {
                viewModel.nextTime()
            }

            tvNotificationByMonth.setOnClickListener {
                setViewSelected(it)
                viewModel.setChooseTimeMode(ChooseTimeMode.MONTH)
            }

            tvNotificationByYear.setOnClickListener {
                setViewSelected(it)
                viewModel.setChooseTimeMode(ChooseTimeMode.YEAR)
            }
        }
    }

    override fun getViewModel(): BaseViewModel = viewModel

    private fun initRcvCategory() {
        with(binding) {
            rvNotificationCategory.adapter = noteAdapter
        }
    }

    private fun setViewSelected(viewNew: View) {
        viewSelected?.isSelected = false
        viewNew.isSelected = true
        viewSelected = viewNew
    }

}