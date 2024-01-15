package com.duongph.moneynote.presenter.home

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.duongph.moneynote.domain.model.DateTimeObject
import com.duongph.moneynote.domain.model.MoneyNote
import com.duongph.moneynote.getMoneyClearText
import com.duongph.moneynote.presenter.adapter.CategoryAdapter
import com.duongph.moneynote.presenter.base.BaseFragment
import com.duongph.moneynote.presenter.base.BaseViewModel
import com.example.mynotehilt.databinding.FragmentHomeBinding
import java.util.*

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel: HomeViewModel by viewModels()

    private val categoryAdapter = CategoryAdapter()


    private var viewSelected: View? = null

    private val datePickerDialog: DatePickerDialog by lazy {
        DatePickerDialog(requireContext())
    }

    override fun initObserve() {
        with(viewModel) {
            dateLiveData.observe {
                binding.textCurrentDate.text = it
            }

            categoryLiveData.observe {
                categoryAdapter.setData(it, true)
            }

            moneyLiveData.observe {
                viewModel.getCategory()
            }
            addNoteState.observe { event ->
                if (event.getContentIfNotHandled() == true) {
                    parentFragmentManager.setFragmentResult("update", Bundle())
                    Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun initView() {
        with(binding) {
            setViewSelected(tvHomeMoneyOut)
            initRcvCategory()
        }
    }

    override fun initListener() {
        with(binding) {

            tvHomeMoneyOut.setOnClickListener {
                viewModel.setMoneyOut(true)
                setViewSelected(it)
            }
            tvHomeMoneyIn.setOnClickListener {
                viewModel.setMoneyOut(false)
                setViewSelected(it)
            }

            ivNextDay.setOnClickListener {
                viewModel.nextDay()
            }
            ivPrevDay.setOnClickListener {
                viewModel.preDay()
            }

            textCurrentDate.setOnClickListener {
                datePickerDialog.show()
            }

            btnSave.setOnClickListener {

                if (validateNote()) {
                    val note = MoneyNote(
                        money = edtMoney.text.toString().getMoneyClearText(),
                        note = edtNote.text.toString(),
                        category = categoryAdapter.getItemSelected(),
                        dateTimeObject = DateTimeObject(date = viewModel.getDateLong()),
                        typeMoney = categoryAdapter.getItemSelected().typeMoney
                    )
                    viewModel.addNote(note)
                    edtMoney.setText("")
                    edtNote.setText("")
                }

            }

            datePickerDialog.setOnDateSetListener { _, year, month, dayOfMonth ->
                val calendar = Calendar.getInstance()
                calendar.set(year, month, dayOfMonth)
                viewModel.setDate(calendar)
            }
        }
    }

    private fun validateNote(): Boolean {
        if (binding.edtMoney.text.toString().isEmpty()) {
            showToast("Vui lòng nhập số tiền")
            return false
        }
        return true
    }

    private fun setViewSelected(viewNew: View) {
        viewSelected?.isSelected = false
        viewNew.isSelected = true
        viewSelected = viewNew
    }

    private fun initRcvCategory() {
        with(binding) {
            categoryAdapter.onItemClick = {

            }
            categoryAdapter.onAddItemClick = {
                findNavController().navigate(HomeFragmentDirections.viewCategory())
            }
            rvHomeCategory.adapter = categoryAdapter
        }
    }

    override fun getViewModel(): BaseViewModel = viewModel

}