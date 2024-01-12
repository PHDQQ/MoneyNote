package com.duongph.moneynote.common

import android.content.Context
import android.content.res.Resources
import com.duongph.moneynote.MainApplication


class Const {
    companion object {
        const val CATEGORY = "category"
        const val NOTE = "note"
        val COLORS = arrayOf(
            "#FFE666",
            "#FFC1AE",
            "#FF8D8D",
            "#FFBAE1",
            "#FFCAFF",
            "#FF8000",
            "#FF0000",
            "#FF548F",
            "#ED54B4",
            "#F264FF",
            "#B46800",
            "#AC0003",
            "#B03C4E",
            "#B6006C",
            "#990BA6",
            "#FFFF66",
            "#E2FF66",
            "#DEFFB5",
            "#97FFE0",
            "#66FFFF",
            "#FFE100",
            "#B4FF00",
            "#4FD800",
            "#31D8A2",
            "#00A0F5",
            "#C0A800",
            "#A7BE00",
            "#009600",
            "#007F86",
            "#007AA4",
            "#66B0FF",
            "#E1BFFF",
            "#F3F3F3",
            "#E0E0E0",
            "#767676",
            "#004AFF",
            "#A14AFF",
            "#FFFFFF",
            "#969696",
            "#000000",
            "#0000C8",
            "#5614AD",
            "#B1B1B1",
            "#494949",
            "#2E2E2E",
            "#FF8700",
            "#00B547",
            "#1638A7",
            "#FF54A8",
            "#FFD500",
            "#FF4950",
            "#61E396",
            "#09CEFF",
            "#A9673C",
            "#696969",
            "#FFA400",
            "#4EB46A",
            "#FFB27F",
            "#FF2E00",
            "#00C7FF",
            "#42D2C1",
            "#F98BBE"
        )

        fun getCategoryImages(): List<Int> {
            val arrayList = ArrayList<Int>()
            for (i in 0..140) {
                arrayList.add(getResourceId(i))
            }
            return arrayList
        }

        private fun getResourceId(context: Context, i: Int): Int {
            val resources: Resources = context.resources
            return resources.getIdentifier(
                "category" + String.format("%03d", Integer.valueOf(i)),
                "mipmap",
                context.packageName
            )
        }

        private fun getResourceId(i: Int): Int {
            val context: Context = MainApplication.g().applicationContext
            return context.resources.getIdentifier(
                "category" + String.format("%03d", Integer.valueOf(i)),
                "mipmap", context.packageName
            )
        }

    }


}