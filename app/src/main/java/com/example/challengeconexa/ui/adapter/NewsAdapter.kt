package com.example.challengeconexa.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challengeconexa.databinding.ItemRvUsersHomeBinding
import com.example.challengeconexa.service.model.New

class NewsAdapter(private val itemClickListener: (New) -> Unit): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var list = emptyList<New>()

    inner class NewsViewHolder(private val binding: ItemRvUsersHomeBinding)
        :RecyclerView.ViewHolder(binding.root) { /*View.OnClickListener*/

        //init {
        //    binding.root.setOnClickListener(this)
        //}

        fun bind(new: New, position: Int) {
            binding.tvName.text = new.title
            binding.root.setOnClickListener {
                itemClickListener(new)
            }
        }

        //override fun onClick(v: View?) {
        //    val position = adapterPosition
        //   if (position != RecyclerView.NO_POSITION) {
        //       val name = list.get(position)
        //       itemClickListener.onItemClick(name)
        // }
        //}
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRvUsersHomeBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val name = list.get(position)
        holder.bind(name, position)
    }

    override fun getItemCount(): Int = list.size


    @SuppressLint("NotifyDataSetChanged")
    fun setList(users: List<New>) {
        list = users
        notifyDataSetChanged()
    }


    interface OnItemClickListener {
        fun onItemClick(name: String)
    }

}