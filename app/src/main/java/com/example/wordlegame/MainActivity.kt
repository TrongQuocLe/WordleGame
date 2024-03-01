package com.example.wordlegame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var count = 1;
        var wordToGuess = FourLetterWordList.getRandomFourLetterWord()

        val guessInput = findViewById<EditText>(R.id.guessInput)
        val guess1 = findViewById<TextView>(R.id.guess1)
        val guess2 = findViewById<TextView>(R.id.guess2)
        val guess3 = findViewById<TextView>(R.id.guess3)
        val guess1Check = findViewById<TextView>(R.id.guess1Check)
        val guess2Check = findViewById<TextView>(R.id.guess2Check)
        val guess3Check = findViewById<TextView>(R.id.guess3Check)
        val solution = findViewById<TextView>(R.id.solution)

        val submit = findViewById<Button>(R.id.button)
        submit.setOnClickListener {
            guess1.text = guessInput.text.toString()
            guess1Check.text = checkGuess(guessInput.text.toString().uppercase(), wordToGuess)
            submit.setOnClickListener {
                guess2.text = guessInput.text.toString()
                guess2Check.text = checkGuess(guessInput.text.toString().uppercase(), wordToGuess)
                submit.setOnClickListener {
                    guess3.text = guessInput.text.toString()
                    guess3Check.text = checkGuess(guessInput.text.toString().uppercase(), wordToGuess)
                    submit.setEnabled(false)
                    solution.text = wordToGuess
                }
            }
        }
    }

    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String, wordToGuess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }


}

