package ru.samsung.itacademy.mdev.getusdrate

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

class MainViewModel : ViewModel() {
    val btcRate = MutableLiveData<String>()
    val rateCheckInteractor = RateCheckInteractor()

    fun onCreate() {
        refreshRate()
    }

    fun onRefreshClicked() {
        refreshRate()
    }

    private fun refreshRate() {
        GlobalScope.launch(Dispatchers.Main) {
            val rate = withContext(Dispatchers.IO) {
                rateCheckInteractor.requestRate()
            }
            Log.d(TAG, "btcRate = $rate")
            btcRate.value = rate
        }
    }


    companion object {
        const val TAG = "MainViewModel"
        const val BTC_RATE_URL = "https://api.coindesk.com/v1/bpi/currentprice.json"
    }

    class RateCheckInteractor {
        suspend fun requestRate(): String {
            return withContext(Dispatchers.IO) {
                val response = URL(BTC_RATE_URL).readText()
                val json = JSONObject(response)
                val bpiJson = json.getJSONObject("bpi")
                val usdRate = bpiJson.getJSONObject("USD").getString("rate")
                usdRate
            }
        }
    }

}
