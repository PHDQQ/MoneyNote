package com.duongph.moneynote.data.model

import com.duongph.moneynote.domain.model.TYPE_MONEY

class CategoryResponse(
    var id: String? = null,
    var name: String? = null,
    var color: String? = null,
    var resourceName: String? = null,
    var typeMoney: Int? = null,
    var isMoneyOut: Boolean = false,
)