package ru.samsung.itacademy.mdev.getusdrate

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var textRate: TextView
    private lateinit var textTargetRate: EditText
    private lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    @SuppressLint("SetTextI18n")
    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.btcRate.observe(this) {
            textRate.text = "$it RUB"
        }

        viewModel.onCreate()
    }

    private fun initView() {
        textRate = findViewById(R.id.textUsdRubRate)
        textTargetRate = findViewById(R.id.textTargetRate)
        rootView = findViewById(R.id.rootView)

        findViewById<Button>(R.id.btnRefresh).setOnClickListener {
            viewModel.onRefreshClicked()
        }

        findViewById<Button>(R.id.btnSubscribeToRate).setOnClickListener {
            val targetRate = textTargetRate.text.toString()
            val startRate = viewModel.btcRate.value

            if (targetRate.isNotEmpty() && startRate?.isNotEmpty() == true) {
                RateCheckService.stopService(this)
                RateCheckService.startService(this, targetRate)
            } else {
                val messageResId = when {
                    targetRate.isEmpty() -> R.string.target_rate_empty
                    startRate.isNullOrEmpty() -> R.string.current_rate_empty
                    else -> R.string.generic_error
                }
                Snackbar.make(rootView, messageResId, Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
