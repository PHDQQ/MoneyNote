package com.duongph.moneynote.data.converter

class ListConverter<S, D>(converter: IConverter<S, D>) : IConverter<List<S>?, List<D>?> {
    private val mConverter: IConverter<S, D>

    init {
        mConverter = converter
    }

    override fun convert(source: List<S>): List<D> {
        val result: MutableList<D> = ArrayList()
        for (i in source.indices) {
            result.add(mConverter.convert(source[i]))
        }
        return result
    }
}