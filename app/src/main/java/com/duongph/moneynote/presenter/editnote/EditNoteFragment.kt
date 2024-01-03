package com.duongph.moneynote.presenter.editnote

import android.app.DatePickerDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.duongph.moneynote.domain.model.DateTimeObject
import com.duongph.moneynote.domain.model.MoneyNote
import com.duongph.moneynote.getMoneyClearText
import com.duongph.moneynote.presenter.adapter.CategoryAdapter
import com.duongph.moneynote.presenter.base.BaseFragment
import com.duongph.moneynote.presenter.base.BaseViewModel
import com.example.mynotehilt.databinding.FragmentEditNoteBinding
import java.util.*

class EditNoteFragment : BaseFragment<FragmentEditNoteBinding>() {
    private val viewModel: EditNoteViewModel by viewModels()

    private val datePickerDialog: DatePickerDialog by lazy {
        DatePickerDialog(requireContext())
    }
    private val categoryAdapter = CategoryAdapter()


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
            editNoteLiveData.observe { isSuccess ->
                if (isSuccess) {
                    showToast("Sửa thành công")
                    findNavController().popBackStack()
                }
            }
        }
    }

    override fun initView() {
        with(binding) {
            initRcvCategory()
            if (arguments?.getSerializable("note") != null) {
                viewModel.moneyNoteSelected = arguments?.getSerializable("note") as MoneyNote
                edtNote.setText(viewModel.moneyNoteSelected!!.note)
                edtMoney.requestFocus()
                edtMoney.setText(viewModel.moneyNoteSelected!!.money)
                viewModel.moneyNoteSelected!!.dateTimeObject?.let {
                    val calendar = Calendar.getInstance()
                    calendar.timeInMillis = it.date!!
                    viewModel.setDate(calendar)
                }
            }
        }
    }

    override fun initListener() {
        with(binding) {

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
                        id = viewModel.moneyNoteSelected!!.id,
                        money = edtMoney.text.toString().getMoneyClearText(),
                        note = edtNote.text.toString(),
                        category = categoryAdapter.getItemSelected(),
                        dateTimeObject = DateTimeObject(date = viewModel.getDateLong()),
                        typeMoney = categoryAdapter.getItemSelected().typeMoney
                    )
                    viewModel.saveNote(note)
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

    private fun initRcvCategory() {
        with(binding) {
            categoryAdapter.onItemClick = {

            }
            categoryAdapter.onAddItemClick = {

            }
            rvHomeCategory.adapter = categoryAdapter
        }
    }

    override fun getViewModel(): BaseViewModel = viewModel

}