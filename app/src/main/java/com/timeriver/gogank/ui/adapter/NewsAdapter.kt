package com.timeriver.gogank.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.timeriver.gogank.R
import com.timeriver.gogank.domain.model.AndroidNewsModel
import kotlinx.android.synthetic.main.item_android_news.view.*

class NewsAdapter(private val context: Context) :
    PagingDataAdapter<AndroidNewsModel, NewsAdapter.ViewHolder>(COMPARATOR) {

    var onItemClickListener: ((AndroidNewsModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.item_android_news, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.run {
            holder.tvDesc.text = desc
            holder.tvAuthor.text = who
            holder.chipType.text = type
            holder.chipId.text = position.toString()
            holder.itemView.setOnClickListener {
                onItemClickListener?.invoke(this)
            }
        }
    }

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<AndroidNewsModel>() {

            override fun areContentsTheSame(
                oldItem: AndroidNewsModel,
                newItem: AndroidNewsModel
            ): Boolean = oldItem == newItem

            override fun areItemsTheSame(
                oldItem: AndroidNewsModel,
                newItem: AndroidNewsModel
            ): Boolean = oldItem.id == newItem.id
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDesc: TextView = itemView.tv_author_item_android_news
        val tvAuthor: TextView = itemView.tv_desc_item_android_news
        val chipType: Chip = itemView.chip_type_item_android_news
        val chipId: Chip = itemView.chip_id_item_android_news
    }
}


