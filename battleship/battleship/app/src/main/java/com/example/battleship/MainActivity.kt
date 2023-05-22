package com.example.battleship

import android.widget.GridLayout

import android.os.Bundle
import android.widget.Button
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.max
import kotlin.math.min

class MainActivity : AppCompatActivity() {

    private lateinit var gridView: GridView
    private lateinit var startButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridView = findViewById(R.id.grid_view)
        startButton = findViewById(R.id.start_button)

        startButton.setOnClickListener {
            val field = generate()
            gridView.adapter = GridAdapter(this, field)
        }
    }

    private fun generate(): Array<IntArray> {
        val fieldSize = 10
        val field = Array(fieldSize) { IntArray(fieldSize) }
        val shipSizes = listOf(4, 3, 3, 2, 2, 2, 1, 1, 1, 1)

        fun isSafeToPlace(row: Int, col: Int): Boolean {
            val rowStart = max(0, row - 1)
            val rowEnd = min(fieldSize - 1, row + 1)
            val colStart = max(0, col - 1)
            val colEnd = min(fieldSize - 1, col + 1)

            for (i in rowStart..rowEnd) {
                for (j in colStart..colEnd) {
                    if (field[i][j] == 1) {
                        return false
                    }
                }
            }
            return true
        }

        for (shipSize in shipSizes) {
            var placed = false
            while (!placed) {
                val row = (0 until fieldSize).random()
                val col = (0 until fieldSize).random()
                val direction = listOf("horizontal", "vertical").random()

                if (direction == "horizontal") {
                    if (col + shipSize <= fieldSize) {
                        var canPlace = true
                        for (i in 0 until shipSize) {
                            if (!isSafeToPlace(row, col + i)) {
                                canPlace = false
                                break
                            }
                        }

                        if (canPlace) {
                            for (i in 0 until shipSize) {
                                field[row][col + i] = 1
                            }
                            placed = true
                        }
                    }
                } else {
                    if (row + shipSize <= fieldSize) {
                        var canPlace = true
                        for (i in 0 until shipSize) {
                            if (!isSafeToPlace(row + i, col)) {
                                canPlace = false
                                break
                            }
                        }

                        if (canPlace) {
                            for (i in 0 until shipSize) {
                                field[row + i][col] = 1
                            }
                            placed = true
                        }
                    }
                }
            }
        }

        return field
    }

}

