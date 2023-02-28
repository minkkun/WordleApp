package com.example.wordleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.wordleapp.FourLetterWordList.*
import com.example.wordleapp.FourLetterWordList.FourLetterWordList.getRandomFourLetterWord

class MainActivity : AppCompatActivity() {
    var wordToGuess = FourLetterWordList.FourLetterWordList.getRandomFourLetterWord();
    var count = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set the result to be hidden
        val answer = findViewById<TextView>(R.id.answer)
        answer.text = wordToGuess;
        answer.visibility = View.GONE

        val button = findViewById<Button>(R.id.button)
        var record = findViewById<TextView>(R.id.record)
        var guessBox = findViewById<EditText>(R.id.guessBox)
        var dummyRecord = StringBuilder();


        button.setOnClickListener {
            if (count < 4) {
                var guess = guessBox.text.toString().uppercase()
                var check = checkGuess(guess)
                if (count == 3) {
                    answer.visibility = View.VISIBLE
                }
                dummyRecord.appendLine("Word guess #$count: $guess")
                dummyRecord.appendLine("Word check #$count: $check")
                record.text = dummyRecord.toString()
                count++
                guessBox.setText("")
            }
            else {
                Toast.makeText(applicationContext, "Out of guesses, new Wordle", Toast.LENGTH_SHORT).show()
                wordToGuess = FourLetterWordList.FourLetterWordList.getRandomFourLetterWord();
                answer.text = wordToGuess
                answer.visibility = View.GONE
                record.text = null
                guessBox.setText("")
                dummyRecord = StringBuilder();
                count = 1;
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
    private fun checkGuess(guess: String) : String {
        if (guess.length != 4) {
            Toast.makeText(applicationContext, "Input is not 4 letters", Toast.LENGTH_SHORT).show()
        }
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