package com.duongph.moneynote.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConvertersDb {
    @TypeConverter
    fun listToJson(value: List<String>?): String? {
        return if (!value.isNullOrEmpty()) {
            val gson = Gson()
            gson.toJson(value)
        } else {
            ""
        }
    }

    @TypeConverter
    fun jsonToList(value: String?): List<String> {
        return if (value.isNullOrEmpty()) {
            arrayListOf()
        } else {
            val listType = object : TypeToken<List<String>>() {}.type
            Gson().fromJson(value, listType)
        }
    }
}