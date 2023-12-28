package com.duongph.moneynote.domain.model

import android.os.Parcelable
import java.io.Serializable
import java.math.BigDecimal

class MoneyNote(
    var id: String? = null,
    var money: String? = null,
    var dateTimeObject: DateTimeObject? = null,
    var note: String? = null,
    var category: Category? = null,
    var typeMoney: TYPE_MONEY = TYPE_MONEY.MONEY_OUT,
): Serializable

class DateTimeObject(
    var date: Long? = null,
    var yearString: String? = null,
    var monthString: String? = null,
    var dayString: String? = null,
    var dateString: String? = null,
)

class MoneyInfo {
    var listMoneyIn = ArrayList<MoneyNote>()
    var listMoneyOut = ArrayList<MoneyNote>()
    var moneyIn = BigDecimal(0)
    var moneyOut = BigDecimal(0)
}