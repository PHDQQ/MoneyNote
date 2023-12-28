package com.example.mynotehilt.ui.base.adapter

open class BaseVHData<T>(data: T) {
    var type = 0
    var isSelected = false
    var realData: T? = data
}