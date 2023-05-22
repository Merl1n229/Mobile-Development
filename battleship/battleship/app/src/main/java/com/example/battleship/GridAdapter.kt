package com.example.battleship

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class GridAdapter(private val context: Context, private val field: Array<IntArray>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int = field.size * field[0].size

    override fun getItem(position: Int): Any? = null

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: inflater.inflate(R.layout.grid_item, parent, false)
        val textView = view.findViewById<TextView>(R.id.cell_text)
        val row = position / field.size
        val col = position % field[0].size
        textView.text = if (field[row][col] == 1) "1" else ""

        return view
    }
}
