package com.example.project.presentation.Main.StudentsAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.project.domain.User

object StudentsDiffCallback : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}