package com.duongph.moneynote

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

val simpleFormatter = SimpleDateFormat("dd/MM/yyyy")
val monthFormatter = SimpleDateFormat("MM/yyyy")
val yearFormatter = SimpleDateFormat("yyyy")
val gson = Gson()

fun Date.getCurrentMonth(): String {
    return monthFormatter.format(this)
}

fun Date.getCurrentYear(): String {
    return yearFormatter.format(this)
}

fun String.getCurrentMonth(): String {
    return try {
        return simpleFormatter.parse(this).getCurrentMonth()
    } catch (e: Exception) {
        ""
    }
}

fun String.getCurrentYear(): String {
    return try {
        return simpleFormatter.parse(this).getCurrentYear()
    } catch (e: Exception) {
        ""
    }
}

fun Date.getToDay(): String {
    return simpleFormatter.format(this)
}

fun Long.getToDay(): String {
    return simpleFormatter.format(Date(this))
}

fun Long.getToDay2(): String {
    return try {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = this
        "${simpleFormatter.format(Date(this))} (${calendar.getNameDayOfWeek()})"
    } catch (e: Exception) {
        ""
    }
}

fun String.getMoney(): String {
    if (this.isNullOrEmpty()) return ""
    val formatter =
        DecimalFormat.getCurrencyInstance(Locale("vi", "VN")) as DecimalFormat
    return formatter.format(this.toBigDecimal())
}

fun String.getMoneyClearText(): String {
    if (this.isNullOrEmpty()) return ""
    return this.replace(",", "")
}

fun Calendar.getNameDayOfWeek(): String {
    return when (this[Calendar.DAY_OF_WEEK]) {
        Calendar.SUNDAY -> {
            "Chủ nhật"
        }

        Calendar.MONDAY -> {
            "Thứ 2"
        }

        Calendar.TUESDAY -> {
            "Thứ 3"
        }

        Calendar.WEDNESDAY -> {
            "Thứ 4"
        }

        Calendar.THURSDAY -> {
            "Thứ 5"
        }

        Calendar.FRIDAY -> {
            "Thứ 6"
        }

        Calendar.SATURDAY -> {
            "Thứ 7"
        }

        else -> {
            ""
        }
    }
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.setBackgroundColorCompat(color: String) {
    setBackgroundColor(Color.parseColor(color))
}

fun View.setBackgroundCompat(idDrawable: Int) {
    background = ContextCompat.getDrawable(this.context, idDrawable)
}

fun TextView.setTextColorCompat(idColor: Int) {
    this.setTextColor(ContextCompat.getColor(this.context, idColor))
}


data class QueryResponse(val packet: QuerySnapshot?, val error: FirebaseFirestoreException?)

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun Query.awaitRealtime() = suspendCancellableCoroutine<QueryResponse> { continuation ->
    Log.d("getNoteListLive", "awaitRealtime: ")
    addSnapshotListener { value, error ->
        Log.d("getNoteListLive", "awaitRealtime: " + value.toString())

        if (error == null && continuation.isActive)
            continuation.resume(QueryResponse(value, null)) {

            }
        else if (error != null && continuation.isActive)
            continuation.resume(QueryResponse(null, error)) {

            }
    }
}

@MainThread
inline fun <reified VM : ViewModel> ComponentActivity.viewModels(
    noinline extrasProducer: (() -> CreationExtras)? = null,
    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<VM> {
    val factoryPromise = factoryProducer ?: {
        defaultViewModelProviderFactory
    }

    return ViewModelLazy(
        VM::class,
        { viewModelStore },
        factoryPromise,
        { extrasProducer?.invoke() ?: this.defaultViewModelCreationExtras }
    )
}

fun Any.toJson(): String {
    return gson.toJson(this)
}

fun String.getResourceId(): Int {
    val PACKAGE_NAME: String = MainApplication.g().applicationContext.packageName
    return MainApplication.g().applicationContext.resources.getIdentifier(
        "$PACKAGE_NAME:mipmap/$this",
        null,
        null
    )
}
