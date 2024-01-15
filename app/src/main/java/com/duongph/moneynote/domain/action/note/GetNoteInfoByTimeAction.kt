package com.duongph.moneynote.domain.action.note

import android.util.Log
import com.duongph.moneynote.AppData
import com.duongph.moneynote.action.BaseAction
import com.duongph.moneynote.domain.RepoFactory
import com.duongph.moneynote.domain.model.MoneyInfo
import com.duongph.moneynote.domain.model.TYPE_MONEY
import com.duongph.moneynote.getToDay3
import com.duongph.moneynote.toJson
import java.math.BigDecimal
import java.util.Calendar
import java.util.Date

class GetNoteInfoByTimeAction :
    BaseAction<GetNoteInfoByTimeAction.GetNoteInfoByTimeRV, MoneyInfo>() {

    override suspend fun execute(requestValue: GetNoteInfoByTimeRV): MoneyInfo {
        if (AppData.listNoteOut.isEmpty()) {
        }
        val moneyInfo = getByDate(requestValue.timeStamp)
        return moneyInfo
    }

    private suspend fun getByDate(timeStamp: Long): MoneyInfo {
        Log.d("duongph", "getByDate0: " + timeStamp.getToDay3())
        val date = Date(timeStamp)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeStamp
        Log.d("duongph", "getByDate00: " + calendar.timeInMillis.getToDay3())
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val time1 = calendar.timeInMillis
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
        val time2 = calendar.timeInMillis
        val list = RepoFactory.getNoteRepo().getMoneyNoteByTime(time1, time2)
        Log.d("duongph", "getByDate: " + list.toJson())
        val moneyInfo = MoneyInfo()
        list.forEach {
            if (TYPE_MONEY.MONEY_IN == it.category?.typeMoney) {
                moneyInfo.listMoneyIn.add(it)
                moneyInfo.moneyIn = moneyInfo.moneyIn.plus(BigDecimal(it.money))
            }

            if (TYPE_MONEY.MONEY_OUT == it.category?.typeMoney) {
                moneyInfo.listMoneyOut.add(it)
                    moneyInfo.moneyOut = moneyInfo.moneyOut.plus(BigDecimal(it.money))
            }
        }
        moneyInfo.listMoneyOut.addAll(moneyInfo.listMoneyIn)
        return moneyInfo
    }

    class GetNoteInfoByTimeRV : RequestValue {
        var timeStamp = 0L
        var month = ""
        var year = ""
    }
}