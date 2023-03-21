package com.example.currentweatherdatabinding

// my imports
import android.os.Build
import com.example.currentweatherdatabinding.weatherLogic.WeatherData
import com.example.currentweatherdatabinding.databinding.HomeFragmentBinding
import com.example.currentweatherdatabinding.weatherAdapterLogic.WeatherAdapter
import com.google.gson.Gson
import android.widget.Toast
import kotlinx.coroutines.*

// existed imports
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.InputStream
import java.net.URL
import java.util.*

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {
    private var weatherDataList =  mutableListOf<WeatherData>()
    private lateinit var weatherAdapter: WeatherAdapter
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherAdapter = WeatherAdapter(LayoutInflater.from(binding.root.context)) {
            WeatherDialog(binding.root.context) { choice ->
                if (choice === "Full info") {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToWeatherFullInfoFragment(it)
                    )
                }
                else {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToWeatherShortInfoFragment(it)
                    )
                }
            }.show(parentFragmentManager, "weatherDialog")
        }
        binding.weatherItem.adapter = weatherAdapter
        weatherAdapter.WeatherList = weatherDataList
        binding.searchButton.setOnClickListener{ buttonListener() }

    }

    private suspend fun loadWeather(city: String?) {
        val api_key = getString(R.string.api_key)
        val api_url = "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$api_key&units=metric"

        try {
            val stream = withContext(Dispatchers.IO) { URL(api_url).content } as InputStream
            val data = Scanner(stream).nextLine()

            val weather = Gson().fromJson(data, WeatherData::class.java)
            weatherDataList.add(weather)

            withContext(Dispatchers.Main) { weatherAdapter.WeatherList = weatherDataList}
        }
        catch (e: java.lang.Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(binding.root.context, "You type wrong city, try again!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    public fun buttonListener() {
        val city = binding.cityForSearch.text.toString()
        GlobalScope.launch (Dispatchers.IO) { loadWeather(city) }
    }
}