package com.example.project.presentation.Main.StudentsAdapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.project.databinding.StudentsItemBinding
import com.example.project.domain.User

class StudentsAdapter : ListAdapter<User, StudentsViewHolder>(StudentsDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsViewHolder {
        val binding = StudentsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StudentsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentsViewHolder, position: Int) {
        val user = getItem(position)
      
        holder.binding.tvStudent.text = user.name + " " + user.surName
    }
}