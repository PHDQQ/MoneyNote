package com.duongph.moneynote.data.converter

import kotlin.reflect.KClass

interface IDestinationFactory<S : Any, D : Any> {
    fun createDestination(destClass: KClass<D>, src: S): D
}