package com.example.project.presentation.Auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.project.R
import com.example.project.databinding.FragmentStartBinding


class StartFragment : Fragment() {

    private var _binding : FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStudent.setOnClickListener {
            findNavController().navigate(StartFragmentDirections.actionStartFragmentToLoginFragment("Student"))
        }

        binding.btnTeacher.setOnClickListener {
            findNavController().navigate(StartFragmentDirections.actionStartFragmentToLoginFragment("Teacher"))
        }
    }
}