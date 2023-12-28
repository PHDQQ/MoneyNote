package com.duongph.moneynote.action

interface ActionCallBack<T> {
    fun onSuccess(result: T)
    fun onException(e: Exception)
}