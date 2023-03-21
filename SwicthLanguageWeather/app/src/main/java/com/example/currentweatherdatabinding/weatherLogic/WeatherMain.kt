package com.example.currentweatherdatabinding.weatherLogic

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherMain (
    @SerializedName("temp") val temp: String?,
    @SerializedName("temp_max") val temp_max: String?,
    @SerializedName("temp_min") val temp_min: String?,
    @SerializedName("pressure") val pressure: String?,
    @SerializedName("feels_like") val feels_like: String?,
    @SerializedName("humidity") val humidity: String?):Parcelable