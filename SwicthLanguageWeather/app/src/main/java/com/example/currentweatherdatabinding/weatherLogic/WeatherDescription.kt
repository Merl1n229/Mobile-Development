package com.example.currentweatherdatabinding.weatherLogic

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class WeatherDescription (@SerializedName("icon") val icon : String?) : Parcelable