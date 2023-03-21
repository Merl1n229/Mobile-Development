package com.example.currentweatherdatabinding

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.currentweatherdatabinding.databinding.FullInfoFragmentBinding

class WeatherInfoFullFragment : Fragment() {
    private val args by navArgs<WeatherInfoFullFragmentArgs>()
    private lateinit var binding: FullInfoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FullInfoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            with(args.weatherData){
                city.text = name
                temp.text = main.temp + "°C"
                minTemp.text = main.temp_min + "°C"
                maxTemp.text = main.temp_max + "°C"
                feelsLike.text = main.feels_like + "°C"
                windSpeed.text = wind.speed + "%"
                humidity.text = main.humidity
                pressure.text = main.pressure
            }
        }
        binding.root.findViewById<Button>(R.id.btnBack).setOnClickListener { v -> v.findNavController().popBackStack() }
    }

}