package com.example.lamps

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var gridLayout: GridLayout
    private lateinit var scoreTextView: TextView
    private lateinit var resetButton: Button

    private var gridSize = 2
    private var score = 0

    private lateinit var gameBoard: Array<Array<Boolean>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridLayout = findViewById(R.id.grid_layout)
        scoreTextView = findViewById(R.id.score_text_view)
        resetButton = findViewById(R.id.reset_button)

        resetButton.setOnClickListener {
            resetGame()
        }

        createGameBoard()
        updateGameBoard()
    }

    private fun createGameBoard() {
        gameBoard = Array(gridSize) { Array(gridSize) { Random.nextBoolean() } }
    }

    private fun updateGameBoard() {
        gridLayout.removeAllViews()

        for (i in 0 until gridSize) {
            for (j in 0 until gridSize) {
                val circleView = View(this)
                val layoutParams = GridLayout.LayoutParams().apply {
                    width = 100
                    height = 100
                    setGravity(Gravity.CENTER)
                    columnSpec = GridLayout.spec(j)
                    rowSpec = GridLayout.spec(i)
                }

                if (gameBoard[i][j]) {
                    circleView.setBackgroundResource(R.drawable.circle_filled)
                } else {
                    circleView.setBackgroundResource(R.drawable.circle_outline)
                }

                circleView.setOnClickListener {
                    toggleCircle(i, j)
                }

                gridLayout.addView(circleView, layoutParams)
            }
        }

        scoreTextView.text = "Score: $score"
    }

    private fun toggleCircle(row: Int, col: Int) {
        gameBoard[row][col] = !gameBoard[row][col]

        for (i in 0 until gridSize) {
            if (i != row) {
                gameBoard[i][col] = !gameBoard[i][col]
            }
            if (i != col) {
                gameBoard[row][i] = !gameBoard[row][i]
            }
        }

        score++

        if (checkWin()) {
            val toast = Toast.makeText(applicationContext, "Ура! Вы победили со счетом $score", Toast.LENGTH_SHORT)
            toast.show()
            gridSize++
            createGameBoard()
        }

        updateGameBoard()
    }

    private fun checkWin(): Boolean {
        val firstCircle = gameBoard[0][0]

        for (i in 0 until gridSize) {
            for (j in 0 until gridSize) {
                if (gameBoard[i][j] != firstCircle) {
                    return false
                }
            }
        }

        return true
    }

    private fun resetGame() {
        score = 0
        gridSize = 4
        createGameBoard()
        updateGameBoard()
    }
}
