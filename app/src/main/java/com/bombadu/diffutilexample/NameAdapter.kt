package com.bombadu.diffutilexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class NameAdapter : ListAdapter<Name, NameAdapter.NameHolder>(NamesDiffCallBack()) {


    override fun onBindViewHolder(holder: NameHolder, position: Int) {
        val currentName = getItem(position)
        holder.textViewName.text = currentName.name
    }


    class NamesDiffCallBack : DiffUtil.ItemCallback<Name>() {
        override fun areItemsTheSame(oldItem: Name, newItem: Name): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Name, newItem: Name): Boolean {
            return oldItem.name == newItem.name
        }

    }

    class NameHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewName: TextView = itemView.findViewById(R.id.list_item_item)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return NameHolder(itemView)
    }


    fun getNameAt(position: Int): Name? {
        return getItem(position)
    }


}