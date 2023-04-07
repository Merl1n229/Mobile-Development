package ru.samsung.itacademy.mdev.getusdrate

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

class NetworkClient(private val context: Context) {

    val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    fun isInternetConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager?.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(network) ?: return false
            return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        } else {
            val activeNetwork = connectivityManager?.activeNetworkInfo
            return activeNetwork?.isConnectedOrConnecting ?: false
        }
    }

    fun request(url: String): String? {
        if (!isInternetConnected()) {
            return null
        }

        val requestBuilder = Request.Builder()
            .url(url)
            .build()

        try {
            val response = client.newCall(requestBuilder).execute()
            if (response.isSuccessful) {
                return response.body?.string()
            } else {
                Log.e("NetworkClient", "network error: ${response.code}")
            }
        } catch (e: IOException) {
            Log.e("NetworkClient", "error during network call", e)
        } catch (e: Exception) {
            Log.e("NetworkClient", "unknown error during network call", e)
        }

        return null
    }
}
