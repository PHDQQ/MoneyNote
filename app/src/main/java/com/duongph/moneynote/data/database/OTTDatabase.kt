package com.duongph.moneynote.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.duongph.moneynote.MainApplication
import com.duongph.moneynote.data.database.dao.IMoneyDAO
import com.duongph.moneynote.data.database.entities.NoteEntity


@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = false
)

abstract class MoneyDatabase : RoomDatabase() {

    companion object {
        const val SQLITE_MAX_VARIABLE_NUMBER = 999
        private var INSTANCE: MoneyDatabase? = null
        fun g(): MoneyDatabase {
            if (INSTANCE == null) {

                synchronized(MoneyDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        MainApplication.g().applicationContext,
                        MoneyDatabase::class.java,
                        "money"
                    )
                        .allowMainThreadQueries().build()
                }
            }

            return INSTANCE!!
        }

        fun removeDatabase() {
            MainApplication.g().applicationContext.deleteDatabase("money")
            INSTANCE = null
        }
    }

    abstract fun ottMessageDao(): IMoneyDAO
}