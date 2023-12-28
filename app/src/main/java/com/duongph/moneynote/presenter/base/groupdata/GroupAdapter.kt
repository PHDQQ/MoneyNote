package com.duongph.moneynote.presenter.base.groupdata

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.duongph.moneynote.presenter.base.adapter.BaseRcvVH

class GroupAdapter : RecyclerView.Adapter<BaseRcvVH<*>>() {

    private val groupManager: GroupManager = GroupManager(this)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRcvVH<*> {
        val layoutId = groupManager.getLayoutResource(viewType)

        val itemView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

        return groupManager.onCreateVH(itemView, viewType)
    }

    override fun onBindViewHolder(holder: BaseRcvVH<*>, position: Int) {
//        Log.e("GROUP_DATA", "onBindViewHolder: position")
        if (holder is GroupVH<*, *>) {
            groupManager.onBindViewHolder(holder as GroupVH<Any, GroupData<*>>, position)
        }
    }

    override fun onBindViewHolder(
        holder: BaseRcvVH<*>,
        position: Int,
        payloads: MutableList<Any>
    ) {
//        Log.e("GROUP_DATA", "onBindViewHolder payload: position")
        if (payloads.isNotEmpty() && holder is GroupVH<*, *>) {
            groupManager.onBindViewHolder(holder as GroupVH<Any, GroupData<*>>, position, payloads)
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return groupManager.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return groupManager.getItemCount()
    }

    fun notifyAllGroupChanged() {
        groupManager.notifyDataSetChanged()
    }

    fun addGroup(data: GroupData<*>) {
        groupManager.addGroupData(data)
    }

    fun addGroupDataAtIndex(index: Int, data: GroupData<*>) {
        groupManager.addGroupDataAtIndex(index, data)
    }

    fun removeGroup(groupData: GroupData<*>?) {
        if (groupData != null) {
            groupManager.removeGroup(groupData)
        }
    }

    fun removeGroupWithoutNotify(groupData: GroupData<*>?) {
        if (groupData != null) {
            groupManager.removeGroupWithoutNotify(groupData)
        }
    }

    fun clear() {
        groupManager.clear()
    }


    fun findGroupDataByAdapterPosition(adapterPosition: Int): GroupData<*>? {
        return groupManager.findGroupDataByAdapterPosition(adapterPosition)
    }

    fun getItemDataAtAdapterPosition(adapterPosition: Int): Any {
        return groupManager.getItemDataAtAdapterPosition(adapterPosition)
    }


    fun getPositionInGroup(adapterPosition: Int): Int {
        val group: GroupData<*>? = groupManager.findGroupDataByAdapterPosition(adapterPosition)
        return if (group != null) {
            adapterPosition - group.adapterPosition
        } else -1
    }

    fun getIndexOfGroup(groupData: GroupData<*>): Int {
        return groupManager.getIndexOfGroupData(groupData)
    }

    fun addRawGroupAtIndex(index: Int, groupData: GroupData<*>) {
        groupManager.addRawGroupDataAtIndex(index, groupData)
    }
}