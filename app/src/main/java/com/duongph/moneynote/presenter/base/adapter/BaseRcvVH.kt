package com.duongph.moneynote.presenter.base.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mynotehilt.ui.base.adapter.IOnBind


abstract class BaseRcvVH<T>(itemView: View) : ViewHolder(itemView), IOnBind<T> {

    init {
        onInitView(itemView)
    }

    final override fun onInitView(itemView: View) {

    }

    abstract override fun onBind(data: T)

    override fun onBind(data: T, payload: List<Any>) {

    }

    fun clickOn(
        view: View,
        listener: View.OnClickListener?
    ) {
        if (listener != null) {
            view.setOnClickListener {
                if (adapterPosition > -1) {
                    listener.onClick(view)
                }
            }
        }
    }

    fun longClickOn(
        view: View,
        listener: View.OnLongClickListener?
    ) {
        if (listener != null) {
            view.setOnLongClickListener {
                if (adapterPosition > -1) {
                    return@setOnLongClickListener listener.onLongClick(view)
                }
                true
            }
        }
    }
}