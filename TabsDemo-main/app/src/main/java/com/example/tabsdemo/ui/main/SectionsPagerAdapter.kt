package com.example.tabsdemo.ui.main

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.tabsdemo.R
import com.example.tabsdemo.WeatherFragment


class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return WeatherFragment.newInstance(context.resources.getStringArray(R.array.cities)[position])
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getStringArray(R.array.cities)[position]
    }

    override fun getCount(): Int {
        return context.resources.getStringArray(R.array.cities).size
    }
}