package com.example.project.presentation.Main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project.R
import com.example.project.databinding.FragmentPowerBinding

class PowerFragment : Fragment() {

    private var _binding: FragmentPowerBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPowerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPower.setOnClickListener{

            binding.btnPower.isSelected = !binding.btnPower.isSelected
            if (binding.btnPower. isPressed) {
               Log.d("checkshit","poweron")
            }
            else {
                Log.d("checkshit","poweroff")
            }
        }
    }
}