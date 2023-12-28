package com.duongph.moneynote.presenter.dashboard

import android.app.AlertDialog
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.duongph.moneynote.domain.model.MoneyNote
import com.duongph.moneynote.getMoney
import com.duongph.moneynote.gone
import com.duongph.moneynote.presenter.adapter.NoteGroupData
import com.duongph.moneynote.presenter.base.BaseViewModel
import com.duongph.moneynote.presenter.base.groupdata.GroupAdapter
import com.duongph.moneynote.presenter.model.MoneyNotePage
import com.duongph.moneynote.show
import com.duongph.moneynote.toJson
import com.example.mynotehilt.R
import com.example.mynotehilt.databinding.FragmentDashboardBinding
import com.example.mynotehilt.ui.base.BaseFragment

class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {
    private val viewModel: DashboardViewModel by viewModels()
//
    private val adapter = GroupAdapter()

    override fun initObserve() {
        with(viewModel) {
            moneyNotePageLiveData.observe {
                handleAddNoteToList(it)
                binding.tvDashBoardMoneyOut.text = it.moneyOut.toString().getMoney()
                binding.tvDashBoardMoneyIn.text = it.moneyIn.toString().getMoney()
                binding.tvDashBoardMoneyResult.text = it.moneyResult.toString().getMoney()
            }
            delNoteState.observe {
                if (it) {
                    showToast("Xoá thành công")
                }
            }
//
            monthLiveData.observe {
                binding.textCurrentMonth.text = it
            }
        }
    }

    override fun initView() {
        with(binding) {
            val simpleItemTouchHelper = object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
//                    Log.d("duongph", "getItemDataAtAdapterPosition: "+ adapter.getItemDataAtAdapterPosition(position).toJson())
//                    Log.d("duongph", "getPositionInGroup: "+ adapter.getPositionInGroup(position))
                    AlertDialog.Builder(context!!)
                        .setTitle("Xoá")
                        .setMessage("Bạn có chắc chắn muốn xoá note ko?")
                        .setPositiveButton(
                            "Đồng ý"
                        ) { dialog, which ->
                            dialog.dismiss()
                            removeNote(position)
                        }
                        .setNegativeButton("huỷ") { dialog, which ->
                            adapter.notifyItemRemoved(position)
                        }
                        .setIcon(R.drawable.ic_notifications_black_24dp)
                        .show()

                }

            }
            ItemTouchHelper(simpleItemTouchHelper).apply {
                attachToRecyclerView(rcvDashboard)
            }
            rcvDashboard.adapter = adapter
        }
    }

    private fun removeNote(position: Int) {
        val group = adapter.findGroupDataByAdapterPosition(position) as NoteGroupData
        viewModel.deleteNote(adapter.getItemDataAtAdapterPosition(position) as MoneyNote)
        if (group.data.size == 1) {
            adapter.removeGroup(group)
        }else {
            group.removeItem(adapter.getPositionInGroup(position) )
        }
    }

    private fun handleAddNoteToList(moneyPage: MoneyNotePage) {

        if (moneyPage.moneyGroupList.isEmpty()) {
            binding.rcvDashboard.gone()
        } else {
            binding.rcvDashboard.show()
            adapter.clear()
            moneyPage.moneyGroupList.forEach {
                adapter.addGroup(NoteGroupData().apply {
                    this.resetMoneyGroup(it)
                    onClickItem = clickItemMoneyNote()
                })
            }

            adapter.notifyAllGroupChanged()
        }
    }

    private fun clickItemMoneyNote(): ((MoneyNote) -> Unit) = {
        findNavController().navigate(DashboardFragmentDirections.editNote(it))
    }

    override fun initListener() {
        with(binding) {
            ivPrevMonth.setOnClickListener {
                adapter.clear()
                viewModel.preMonth()
            }
            ivNextMonth.setOnClickListener {
                adapter.clear()
                viewModel.nextMonth()
            }
        }
    }

    override fun getViewModel(): BaseViewModel = viewModel
}