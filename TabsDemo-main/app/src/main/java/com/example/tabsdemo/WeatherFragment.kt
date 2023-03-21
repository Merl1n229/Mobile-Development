package com.example.tabsdemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tabsdemo.databinding.FullInfoFragmentBinding
import com.google.gson.Gson
import java.io.InputStream
import java.net.URL
import java.util.*
import android.widget.Toast
import com.example.tabsdemo.weatherLogic.WeatherData
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*

private const val ARG_CITYNAME = "Default"

class WeatherFragment : Fragment() {
    private var _binding: FullInfoFragmentBinding? = null
    private val binding get() = _binding!!

    private var city: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            city = it.getString(ARG_CITYNAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FullInfoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        GlobalScope.launch(Dispatchers.IO) { loadWeather(city) }
    }

    private suspend fun loadWeather(city: String?) {
        val api_key = getString(R.string.api_key)
        val api_url = "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$api_key&units=metric"
        try {
            val stream = withContext(Dispatchers.IO) {URL(api_url).content} as InputStream
            val data = Scanner(stream).nextLine()
            val weatherData = Gson().fromJson(data, WeatherData::class.java)
            withContext(Dispatchers.Main) {
                binding.weatherData = weatherData
                Picasso.get().load("https://openweathermap.org/img/w/${weatherData.weather[0].icon}.png").placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(binding.windAndPrec)

            }
        } catch (e: java.lang.Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(binding.root.context, "API ERROR", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(cityArg: String) =
            WeatherFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CITYNAME, cityArg)
                }
            }
    }
}