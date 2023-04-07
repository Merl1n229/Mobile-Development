package ru.samsung.itacademy.mdev.getusdrate

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.math.BigDecimal


class RateCheckService : Service() {
    val handler = Handler(Looper.getMainLooper())
    var rateCheckAttempt = 0
    lateinit var currentRate: BigDecimal
    lateinit var targetRate: BigDecimal
    val rateCheckInteractor = RateCheckInteractor(this)

    val rateCheckRunnable: Runnable = Runnable {
        requestAndCheckRate()
    }

    private fun requestAndCheckRate() {
        GlobalScope.launch(Dispatchers.IO) {
            val rate = rateCheckInteractor.requestRate()
            if (rate.isNotEmpty()) {
                currentRate = BigDecimal(rate)
                rateCheckAttempt++
                Log.d(TAG, "Current rate: $currentRate, attempt $rateCheckAttempt")
                if (currentRate >= targetRate || rateCheckAttempt >= RATE_CHECK_ATTEMPTS_MAX) {
                    handler.removeCallbacks(rateCheckRunnable)
                    stopSelf()
                } else {
                    handler.postDelayed(rateCheckRunnable, RATE_CHECK_INTERVAL)
                }
            } else {
                Log.e(TAG, "Failed to get rate")
            }
        }
    }

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        currentRate = BigDecimal.ZERO
        targetRate = BigDecimal(intent?.getStringExtra(ARG_TARGET_RATE))

        Log.d(TAG, "onStartCommand targetRate = $targetRate")

        handler.post(rateCheckRunnable)

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(rateCheckRunnable)
    }

    companion object {
        const val TAG = "RateCheckService"
        const val RATE_CHECK_INTERVAL = 5000L
        const val RATE_CHECK_ATTEMPTS_MAX = 100

        const val ARG_TARGET_RATE = "ARG_TARGET_RATE"

        fun startService(context: Context, targetRate: String) {
            context.startService(Intent(context, RateCheckService::class.java).apply {
                putExtra(ARG_TARGET_RATE, targetRate)
            })
        }

        fun stopService(context: Context) {
            context.stopService(Intent(context, RateCheckService::class.java))
        }
    }
}
