package com.duongph.moneynote.domain.action.category

import android.util.Log
import com.duongph.moneynote.action.BaseAction
import com.duongph.moneynote.domain.RepoFactory
import com.duongph.moneynote.domain.model.Category
import com.duongph.moneynote.domain.model.CategoryWithNote
import com.duongph.moneynote.domain.model.TYPE_MONEY
import com.duongph.moneynote.getToDay3
import java.util.Calendar

class GetCategoryWithNoteAction : BaseAction<GetCategoryWithNoteAction.GetCategoryWithNoteActionRV, List<CategoryWithNote>>() {
    override suspend fun execute(requestValue: GetCategoryWithNoteActionRV): List<CategoryWithNote> {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = requestValue.timeStamp
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val time1 = calendar.timeInMillis
        Log.d("duongph", "GetCategoryWithNoteAction1: "+time1)
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
        val time2 = calendar.timeInMillis
        Log.d("duongph", "GetCategoryWithNoteAction2: "+time2)
        val list = RepoFactory.getCategoryRepo().getCategoryWithNote(time1, time2)

        return list.filter {
            it.category!!.typeMoney == requestValue.typeMoney
        }
    }

    class GetCategoryWithNoteActionRV : RequestValue {
        var typeMoney = TYPE_MONEY.MONEY_OUT
        var timeStamp = 0L
    }
}