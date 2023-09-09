package com.example.project.presentation.Main.MainAdapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.project.databinding.MainItemBinding
import com.example.project.domain.NotifyItem

class MainAdapter: ListAdapter<NotifyItem, MainViewHolder>(MainDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = MainItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val notify = getItem(position)
        Log.d("checkshit2",notify.toString())
        holder.binding.tvDate.text = notify.date
        holder.binding.tvTime.text = notify.time
        holder.binding.tvNotification.text = notify.text
    }


}