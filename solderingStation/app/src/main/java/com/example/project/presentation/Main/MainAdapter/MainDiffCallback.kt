package com.example.project.presentation.Main.MainAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.project.domain.NotifyItem

object MainDiffCallback : DiffUtil.ItemCallback<NotifyItem>() {
    override fun areItemsTheSame(oldItem: NotifyItem, newItem: NotifyItem): Boolean {
        return oldItem.time == newItem.time
    }

    override fun areContentsTheSame(oldItem: NotifyItem, newItem: NotifyItem): Boolean {
        return oldItem == newItem
    }
}