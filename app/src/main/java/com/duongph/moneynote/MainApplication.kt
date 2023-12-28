package com.duongph.moneynote

import android.app.Application

class MainApplication : Application() {

    companion object {
        private var INSTANCE: MainApplication? = null

        fun g(): MainApplication {
            return INSTANCE!!
        }
    }

    init {
        INSTANCE = this
    }

}