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
import com.example.currentweatherdatabinding.databinding.ShortInfoFragmentBinding
import com.squareup.picasso.Picasso

class WeatherShortInfoFragment : Fragment() {
    private val args by navArgs<WeatherShortInfoFragmentArgs>()
    private lateinit var binding: ShortInfoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ShortInfoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            with(args.weatherData){
                city.text = name
                temp.text = main.temp + "°C"
                feelsLike.text = main.feels_like + "°C"
                Picasso.get().load("https://openweathermap.org/img/w/${weather[0].icon}.png").into(windAndPrec)
            }
        }
        binding.root.findViewById<Button>(R.id.btnBack).setOnClickListener { v -> v.findNavController().popBackStack() }
    }

}