    package ru.samsung.itacademy.mdev.getusdrate

    import android.content.Context
    import android.util.Log
    import kotlinx.coroutines.Dispatchers
    import kotlinx.coroutines.withContext
    import org.json.JSONObject

    class RateCheckInteractor(context: Context) {
        val networkClient = NetworkClient(context)

        suspend fun requestRate(): String {
            return withContext(Dispatchers.IO) {
                val result = networkClient.request(MainViewModel.BTC_RATE_URL)
                if (!result.isNullOrEmpty()) {
                    parseRate(result)
                } else {
                    ""
                }
            }
        }

        private fun parseRate(jsonString: String): String {
            try {
                return JSONObject(jsonString)
                    .getJSONObject("bpi")
                    .getJSONObject("USD")
                    .getString("rate")
                    .replace(",", "")
            } catch (e: Exception) {
                Log.e("RateCheckInteractor", "", e)
            }
            return ""
        }


    }
