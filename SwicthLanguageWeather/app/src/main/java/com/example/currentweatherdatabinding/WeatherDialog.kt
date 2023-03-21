package com.example.currentweatherdatabinding

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.app.AlertDialog

class WeatherDialog(val ctx: Context, private val onAccept: (choice: String?) -> Unit): DialogFragment() {
    private var choise: String? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val langs = arrayOf("Short info", "Full info")
        var choice = if(choise == langs[0]) 1 else 0
        return ctx.let { it -> AlertDialog.Builder(it)
            .setTitle("Choose weather info type:")
            .setSingleChoiceItems(langs,choice)
            { _, which ->
                choice = which
            }
            .setPositiveButton("OK") { _, _ ->
                choise = langs[choice]
                onAccept(choise!!)
            }
            .setNegativeButton("Cancel"){ _,_ -> }
            .create()
        }
    }
}