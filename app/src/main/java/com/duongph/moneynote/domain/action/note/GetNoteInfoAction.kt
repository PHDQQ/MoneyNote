package com.duongph.moneynote.domain.action.note

import com.duongph.moneynote.AppData
import com.duongph.moneynote.action.BaseAction
import com.duongph.moneynote.domain.RepoFactory
import com.duongph.moneynote.domain.model.MoneyInfo
import com.duongph.moneynote.domain.model.SortMode
import java.math.BigDecimal

class GetNoteInfoAction : BaseAction<GetNoteInfoAction.GetNoteInfoRV, MoneyInfo>() {

    override suspend fun execute(requestValue: GetNoteInfoRV): MoneyInfo {
        if (AppData.listNoteOut.isEmpty()) {
            RepoFactory.getNoteRepo().getMoneyNote()
        }
        val moneyInfo = if (requestValue.typeSort == SortMode.CATEGORY) {
            getByCategory()
        } else {
            getByDate(requestValue.month)
        }
        return moneyInfo
    }

    private fun getByDate(month: String): MoneyInfo {
        val moneyInfo = MoneyInfo()
        if (AppData.listNoteIn.isNotEmpty()) {
            AppData.listNoteIn.forEach {
                if (it.dateTimeObject!!.monthString == month) {
                    moneyInfo.listMoneyIn.add(it)
                    moneyInfo.moneyIn = moneyInfo.moneyIn.plus(BigDecimal(it.money))
                }
            }
        }
        if (AppData.listNoteOut.isNotEmpty()) {
            AppData.listNoteOut.forEach {
                if (it.dateTimeObject!!.monthString == month) {
                    moneyInfo.listMoneyOut.add(it)
                    moneyInfo.moneyOut = moneyInfo.moneyOut.plus(BigDecimal(it.money))
                }
            }
        }
        moneyInfo.listMoneyOut.addAll(moneyInfo.listMoneyIn)
        return moneyInfo
    }

    private fun getByCategory(): MoneyInfo {
        val moneyInfo = MoneyInfo()
        return moneyInfo
    }

    class GetNoteInfoRV : RequestValue {
        var month = ""
        var year = ""
        var typeSort = SortMode.DATE
    }
}