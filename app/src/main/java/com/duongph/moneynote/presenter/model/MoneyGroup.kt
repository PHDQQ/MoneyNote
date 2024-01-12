package com.duongph.moneynote.presenter.model

import android.util.Log
import com.duongph.moneynote.domain.model.Category
import com.duongph.moneynote.domain.model.MoneyNote
import com.duongph.moneynote.domain.model.TYPE_MONEY
import com.duongph.moneynote.getMoney
import com.duongph.moneynote.getToDay2
import java.math.BigDecimal
import java.math.RoundingMode

class MoneyGroup {
    var groupTime: String = ""
    var groupMoney: String = ""
    var dataList: MutableList<MoneyNote> = mutableListOf()
}

class MoneyCategoryGroup {
    var categoryGroup: Category? = null
    var progressMoney: Float = 0F
    var dataList: MutableList<MoneyNote> = mutableListOf()
}

class MoneyNotePage {
    val moneyGroupList: MutableList<MoneyGroup> = mutableListOf()
    val moneyGroupCategoryList: MutableList<MoneyCategoryGroup> = mutableListOf()
    var moneyIn = BigDecimal(0)
    var moneyOut = BigDecimal(0)
    val moneyResult: BigDecimal
        get() {
            return moneyIn.minus(moneyOut)
        }

    fun addNewListByCategoryNote(moneyNoteList: List<MoneyNote>) {

        val groupMap = moneyNoteList.filter { it.typeMoney == TYPE_MONEY.MONEY_OUT }.groupBy {
            it.category?.id ?: ""
        }
        val sum = moneyNoteList.filter{ it.typeMoney == TYPE_MONEY.MONEY_OUT }.sumOf { BigDecimal(it.money) }
        val groupCategoryList = groupMap.keys.toMutableList()
        for (i in 0 until groupCategoryList.size) {
            val groupCategory = groupCategoryList[i]
            moneyGroupCategoryList.add(MoneyCategoryGroup().apply {
                groupMap[groupCategory]?.let { dataList.addAll(it) }
                val a = dataList.sumOf { BigDecimal(it.money) }
                progressMoney = a.divide(sum, 2, RoundingMode.HALF_UP).multiply(BigDecimal(100)).toFloat()
                categoryGroup = dataList.getOrNull(0)?.category
            })
        }
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
                groupMoney = list.sumOf {
                    if (it.typeMoney == TYPE_MONEY.MONEY_OUT) BigDecimal("-" + it.money) else BigDecimal(
                        it.money
                    )
                }.toString()

                addNewMoneyNoteListToPage(groupTime, groupMoney, list)
            }
        }
    }

    private fun addNewMoneyNoteListToPage(
        groupTime: String, groupMoney: String, moneyNoteList: List<MoneyNote>
    ) {
        val firstGroup = moneyGroupList.lastOrNull()
        if (firstGroup?.groupTime != groupTime) {

            val newGroup = MoneyGroup().apply {
                this.groupTime = groupTime
                this.groupMoney = groupMoney.getMoney()
                this.dataList.addAll(moneyNoteList.sortedByDescending { it.dateTimeObject!!.date })
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
    } else {
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