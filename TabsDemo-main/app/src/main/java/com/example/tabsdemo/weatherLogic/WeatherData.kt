package com.example.tabsdemo.weatherLogic

data class WeatherData (
    val weather: List<WeatherDescription>,
    val name: String?,
    val main: WeatherMain,
    val wind: WeatherWindInfo)