package com.duongph.moneynote.domain.model

data class Category(
    var id: String? = null,
    var name: String? = null,
    var color: String? = null,
    var resourceName: String? = null,
    var typeMoney: TYPE_MONEY = TYPE_MONEY.MONEY_OUT,
)

enum class TYPE_MONEY {
    MONEY_OUT, MONEY_IN
}