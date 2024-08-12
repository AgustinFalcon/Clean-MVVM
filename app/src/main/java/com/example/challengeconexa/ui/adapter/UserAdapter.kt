package com.example.challengeconexa.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.challengeconexa.databinding.ItemRvUsersBinding
import com.example.challengeconexa.service.model.New
import com.example.challengeconexa.service.model.User

class UserAdapter(private val itemClickListener: (User) -> Unit) : ListAdapter<User, UserAdapter.UserViewHolder>(DiffCallback()) {

    inner class UserViewHolder(private val binding: ItemRvUsersBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            with(binding) {
                tvName.text = user.firstname
                tvLastName.text = user.lastname
                tvId.text = user.id.toString()
            }
            binding.root.setOnClickListener {
                itemClickListener(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRvUsersBinding.inflate(layoutInflater, parent, false)
        return UserViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val newsItem = getItem(position)
        holder.bind(newsItem)
    }

    class DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}