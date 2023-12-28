package com.duongph.moneynote.presenter.base.groupdata

import android.view.View
import androidx.annotation.LayoutRes
import com.duongph.moneynote.presenter.base.adapter.BaseRcvVH

abstract class GroupData<T>(var data: T) {

    companion object {
        val INVALID_RESOURCE = -1
    }

    var adapterPosition = -1

    var groupManager: GroupManager? = null

    open fun attach() {
        adapterPosition = groupManager?.findAdapterPositionForGroup(this) ?: -1
    }

    open fun detach() {
        adapterPosition = -1
    }

    open fun isAttached(): Boolean {
        return adapterPosition > -1
    }

    fun notifyRemove(groupPosition: Int) {
        if (isAttached()) {
            groupManager?.notifyRemove(this, adapterPosition + groupPosition)
        }
    }

    open fun notifyRemove(groupPosition: Int, count: Int) {
        if (isAttached()) {
            groupManager?.notifyRemove(this, adapterPosition + groupPosition, count)
        }
    }

    fun notifyInserted(groupPosition: Int, count: Int) {
        if (count <= 0) {
            return
        }

        if (!isAttached()) {
            attach()
        }
        groupManager?.notifyInserted(this, adapterPosition + groupPosition, count)
    }

    open fun notifyChanged(groupPosition: Int) {
        if (!isAttached()) {
            attach()
        }
        groupManager?.notifyChanged(this, adapterPosition + groupPosition)
    }

    open fun notifyChanged(groupPosition: Int, payload: Any?) {
        if (!isAttached()) {
            attach()
        }
        groupManager?.notifyChanged(this, adapterPosition + groupPosition, payload)
    }

    open fun notifyChanged() {
        groupManager?.notifyChanged(this)
    }

    open fun notifyDataSetChanged() {
        groupManager?.notifyDataSetChanged()
    }

    open fun notifyChange(payload: Any?) {
        groupManager?.notifyChanged(this, payload)
    }

    open fun notifySelfInserted() {
        groupManager?.notifyNewGroupAdded(this)
    }

    open fun notifySelfRemoved() {
        groupManager?.removeGroup(this)
    }

    open fun mapAdapterPositionToGroupPosition(adapterPosition: Int): Int {
        return adapterPosition - this.adapterPosition
    }

    open fun getAdapterPositionFromGroupPosition(groupPosition: Int): Int {
        return adapterPosition + groupPosition
    }

    abstract fun getItemViewType(position: Int): Int

    abstract fun getDataInGroup(positionInGroup: Int): Any

    @LayoutRes
    abstract fun getLayoutResource(viewType: Int): Int

    abstract fun getCount(): Int

    abstract fun onCreateVH(itemView: View, viewType: Int): BaseRcvVH<*>?
}