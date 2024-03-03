package com.example.wordlegame

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var wordToGuess = FourLetterWordList.getRandomFourLetterWord()
        var wordToGuess = "BILL"

        val guessInput = findViewById<EditText>(R.id.guessInput)
//        guessInput
//        if (guessInput.getText().toString().length < 4) {
//            Toast.makeText(applicationContext, "Please enter 4 letter word", Toast.LENGTH_LONG).show()
//        }

        val guess1 = findViewById<TextView>(R.id.guess1)
        val guess2 = findViewById<TextView>(R.id.guess2)
        val guess3 = findViewById<TextView>(R.id.guess3)
        val guess1Check = findViewById<TextView>(R.id.guess1Check)
        val guess2Check = findViewById<TextView>(R.id.guess2Check)
        val guess3Check = findViewById<TextView>(R.id.guess3Check)

        val submit = findViewById<Button>(R.id.submit)
        val reset = findViewById<Button>(R.id.reset)

        reset.setOnClickListener {
            guess1Check.text = ""
            guess2Check.text = ""
            guess3Check.text = ""
            guess1.text = ""
            guess2.text = ""
            guess3.text = ""
            submit.setEnabled(true)
            wordToGuess = "BBBB"
//            var wordToGuess = FourLetterWordList.getRandomFourLetterWord()
            submit.setOnClickListener {
                guess1.text = guessInput.text.toString()
                guess1Check.text = checkGuess(guessInput.text.toString().uppercase(), wordToGuess)
                if (isWon(guess1Check)) {
                    showCustomAlertDialogBox("Won", wordToGuess)
                    gameEnd(submit)
                }
                submit.setOnClickListener {
                    guess2.text = guessInput.text.toString()
                    guess2Check.text = checkGuess(guessInput.text.toString().uppercase(), wordToGuess)
                    if (isWon(guess2Check)) {
                        gameEnd(submit)
                        showCustomAlertDialogBox("Won", wordToGuess)
                    }
                    submit.setOnClickListener {
                        guess3.text = guessInput.text.toString()
                        guess3Check.text = checkGuess(guessInput.text.toString().uppercase(), wordToGuess)
                        gameEnd(submit)
                        showCustomAlertDialogBox("Lose", wordToGuess)
                    }
                }
            }
        }

        submit.setOnClickListener {
            guess1.text = guessInput.text.toString()
            guess1Check.text = checkGuess(guessInput.text.toString().uppercase(), wordToGuess)
            if (isWon(guess1Check)) {
                gameEnd(submit)
                showCustomAlertDialogBox("Won", wordToGuess)
            }
            submit.setOnClickListener {
                guess2.text = guessInput.text.toString()
                guess2Check.text = checkGuess(guessInput.text.toString().uppercase(), wordToGuess)
                if (isWon(guess2Check)) {
                    gameEnd(submit)
                    showCustomAlertDialogBox("Won", wordToGuess)
                }
                submit.setOnClickListener {
                    guess3.text = guessInput.text.toString()
                    guess3Check.text = checkGuess(guessInput.text.toString().uppercase(), wordToGuess)
                    gameEnd(submit)
                    showCustomAlertDialogBox("Lose", wordToGuess)
                }
            }
        }
    }

//    fun showDialogBox(wordToGuess: String?, guess1Check: TextView, guess2Check: TextView
//    , guess3Check: TextView, guess1: TextView, guess2: TextView, guess3: TextView, guessInput: EditText, submit: Button) {
//        val dialog = Dialog(this)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setCancelable(false)
//        dialog.setContentView(R.layout.game_end_dialog)
//        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//
//        val solution : TextView = dialog.findViewById(R.id.solution)
//        val resetBtn : Button = dialog.findViewById(R.id.resetBtn)
//        val cancelBtn : Button = dialog.findViewById(R.id.cancelBtn)
//
//        solution.text = wordToGuess
//
//        resetBtn.setOnClickListener {
//            guess1Check.text = ""
//            guess2Check.text = ""
//            guess3Check.text = ""
//            guess1.text = ""
//            guess2.text = ""
//            guess3.text = ""
//            solution.text = "****"
//            submit.setEnabled(true)
//            var wordToGuess = "BBBB"
////            var wordToGuess = FourLetterWordList.getRandomFourLetterWord()
//            submit.setOnClickListener {
//                guess1.text = guessInput.text.toString()
//                guess1Check.text = checkGuess(guessInput.text.toString().uppercase(), wordToGuess)
//                if (isWon(guess1Check)) gameEnd(submit)
//                submit.setOnClickListener {
//                    guess2.text = guessInput.text.toString()
//                    guess2Check.text = checkGuess(guessInput.text.toString().uppercase(), wordToGuess)
//                    if (isWon(guess2Check)) gameEnd(submit)
//                    submit.setOnClickListener {
//                        guess3.text = guessInput.text.toString()
//                        guess3Check.text = checkGuess(guessInput.text.toString().uppercase(), wordToGuess)
//                        gameEnd(submit)
//                    }
//                }
//            }
//
//            dialog.dismiss()
//        }
//
//        cancelBtn.setOnClickListener {
//            dialog.dismiss()
//        }
//
//    }

    fun showCustomAlertDialogBox(title: String, solution: String?): Unit {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage("Word to guess is $solution")
        //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            Toast.makeText(applicationContext,
                android.R.string.yes, Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(applicationContext,
                android.R.string.no, Toast.LENGTH_SHORT).show()
        }
        builder.show()

    }
    private fun isWon (guessCheck: TextView): Boolean {
        if (guessCheck.getText() == "OOOO") {
            return true
        }
        return false
    }
    private fun gameEnd (submit: Button) {
        submit.setEnabled(false)
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

