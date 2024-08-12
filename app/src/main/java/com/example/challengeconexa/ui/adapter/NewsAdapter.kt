package com.example.challengeconexa.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.challengeconexa.databinding.ItemRvNewsBinding
import com.example.challengeconexa.service.model.New

class NewsAdapter(private val itemClickListener: (New) -> Unit) : ListAdapter<New, NewsAdapter.NewsViewHolder>(DiffCallback()) {

    inner class NewsViewHolder(private val binding: ItemRvNewsBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(new: New) {
            binding.tvName.text = new.title
            binding.root.setOnClickListener {
                itemClickListener(new)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRvNewsBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = getItem(position)
        holder.bind(newsItem)
    }

    class DiffCallback : DiffUtil.ItemCallback<New>() {
        override fun areItemsTheSame(oldItem: New, newItem: New): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: New, newItem: New): Boolean {
            return oldItem == newItem
        }
    }
}
