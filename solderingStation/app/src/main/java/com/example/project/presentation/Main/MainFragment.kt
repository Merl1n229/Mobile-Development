package com.example.project.presentation.Main


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.project.databinding.FragmentMainBinding

import com.example.project.presentation.Main.MainAdapter.MainAdapter
import com.example.project.presentation.MainApplication
import com.example.project.presentation.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()
    private lateinit var mainAdapter: MainAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainAdapter = MainAdapter()
        binding.rvMain.adapter = mainAdapter

        viewModel.notificationFromArduino()
        viewModel.getNotifications().observe(viewLifecycleOwner){
            Log.d("checkshit1", it.toString())
            mainAdapter.submitList(it)
        }

    }


}

