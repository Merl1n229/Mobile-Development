package com.example.currentweatherdatabinding.weatherLogic

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherData (
    @SerializedName("weather") val weather: List<WeatherDescription>,
    @SerializedName("name") val name: String?,
    @SerializedName("main") val main: WeatherMain,
    @SerializedName("wind") val wind: WeatherWindInfo) : Parcelable