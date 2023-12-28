package com.duongph.moneynote.data.database

import android.database.Cursor

object CursorUtil {
    fun getColumnIndexOrThrow(c: Cursor, name: String): Int {
        val index = c.getColumnIndex(name)
        return if (index >= 0) {
            index
        } else c.getColumnIndexOrThrow("'$name'")
    }

    fun getLongOrNull(c: Cursor, colIndex: Int): Long? {
        if (c.isNull(colIndex)) return null
        return c.getLong(colIndex)
    }
}