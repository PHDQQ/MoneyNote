package com.duongph.moneynote.presenter.model

import com.duongph.moneynote.domain.model.MoneyNote
import com.duongph.moneynote.domain.model.TYPE_MONEY
import com.duongph.moneynote.getMoney
import com.duongph.moneynote.getToDay2
import java.math.BigDecimal

class MoneyGroup {
    var groupTime: String = ""
    var groupMoney: String = ""
    var dataList: MutableList<MoneyNote> = mutableListOf()
}

class MoneyNotePage {
    val moneyGroupList: MutableList<MoneyGroup> = mutableListOf()
    var moneyIn = BigDecimal(0)
    var moneyOut = BigDecimal(0)
    var moneyResult = BigDecimal(0)
        get() {
           return moneyIn.minus(moneyOut)
        }

    fun addNewListMoneyNote(moneyNoteList: List<MoneyNote>) {

        val groupMap = moneyNoteList.sortedByDescending { it.dateTimeObject!!.date }.groupBy {
            it.dateTimeObject?.date!!.getToDay2()
        }

        val groupTimeList = groupMap.keys.toMutableList()
        for (i in 0 until groupTimeList.size) {
            val groupTime = groupTimeList[i]
            var groupMoney = "0"

            groupMap[groupTime]?.let { list ->
                groupMoney = list.sumOf { if (it.typeMoney == TYPE_MONEY.MONEY_OUT) BigDecimal("-"+it.money) else BigDecimal(it.money)}.toString()

                addNewMoneyNoteListToPage(groupTime, groupMoney, list)
            }
        }
    }

    private fun addNewMoneyNoteListToPage(
        groupTime: String,
        groupMoney: String,
        moneyNoteList: List<MoneyNote>
    ) {
        val firstGroup = moneyGroupList.lastOrNull()
        if (firstGroup?.groupTime != groupTime) {

            val newGroup = MoneyGroup().apply {
                this.groupTime = groupTime
                this.groupMoney = groupMoney.getMoney()
                this.dataList.addAll(
                    moneyNoteList.sortedByDescending { it.dateTimeObject!!.date }
                )
            }

            moneyGroupList.addLastItem(newGroup)
        } else {
            firstGroup.dataList.addLastItemList(
                moneyNoteList
            )
        }
    }
}


fun <T> MutableList<T>.addFirstItem(item: T) {
    if (isEmpty()) {
        add(item)
    } else {
        add(0, item)
    }
}


fun <T> MutableList<T>.addLastItem(item: T) {
    if (isEmpty()) {
        add(item)
    }else {
        add(size, item)
    }
}

fun <T> MutableList<T>.addFirstItemList(itemList: List<T>) {
    if (isEmpty()) {
        addAll(itemList)
    } else {
        addAll(0, itemList)
    }
}

fun <T> MutableList<T>.addLastItemList(itemList: List<T>) {
    if (isEmpty()) {
        addAll(itemList)
    } else {
        addAll(size - 1, itemList)
    }
}