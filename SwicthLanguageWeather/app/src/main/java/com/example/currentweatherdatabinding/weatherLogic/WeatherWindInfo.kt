package com.example.currentweatherdatabinding.weatherLogic

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherWindInfo(
    @SerializedName("speed") val speed: String?,
) : Parcelable