package com.example.project.presentation.Main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.project.R
import com.example.project.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type = intent.getStringExtra("type")


        supportActionBar!!.hide()
        Log.d("checkshit", type!!.toString())
        val  navView: BottomNavigationView = binding.bottomNavigationView

        val fragment: Int
        if (type == "Teacher") {
            fragment = R.id.studentsFragment
            navView.menu.add(Menu.NONE, R.id.studentsFragment, Menu.NONE, null)
                .setIcon(R.drawable.airicon)
        } else {
            fragment = R.id.airFragment
            navView.menu.add(Menu.NONE, R.id.airFragment, Menu.NONE, null)
                .setIcon(R.drawable.airicon)
        }
        val navController = findNavController(R.id.main_container)
        val appBarConfiguration = AppBarConfiguration(setOf(
            fragment,
            R.id.powerFragment,
            R.id.mainFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }


    companion object {
        fun newIntent(context: Context, type: String){
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("type", type)
            context.startActivity(intent)
        }
    }
}