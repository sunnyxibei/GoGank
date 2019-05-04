package com.timeriver.gogank.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.timeriver.gogank.R
import com.timeriver.gogank.network.GankBean

class MainAdapter(val data: GankBean) : RecyclerView.Adapter<MainViewHolder>() {

    override fun getItemCount() = data.category.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_main, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.tvName.text = data.category[position]
    }

}

class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val tvName:TextView = itemView.findViewById(R.id.tv_item_name)
}
