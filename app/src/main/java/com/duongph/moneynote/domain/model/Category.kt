package com.duongph.moneynote.domain.model

data class Category(
    var id: String? = null,
    var name: String? = null,
    var color: String? = null,
    var resourceName: String? = null,
    var typeMoney: TYPE_MONEY = TYPE_MONEY.MONEY_OUT,
)

enum class TYPE_MONEY(val value: Int) {
    MONEY_OUT(0), MONEY_IN(1)
}

fun Int.convertEnumTypeMoney(): TYPE_MONEY {
    return when (this) {
        0 -> TYPE_MONEY.MONEY_OUT
        1 -> TYPE_MONEY.MONEY_IN
        else -> {
            throw Exception()
        }
    }
}