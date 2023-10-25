package com.mathsgame.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Locale
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    lateinit var textScore: TextView
    lateinit var textLife: TextView
    lateinit var textTime: TextView

    lateinit var textQuestion: TextView
    lateinit var editTextAnswer: EditText

    lateinit var buttonOk: Button
    lateinit var buttonNext: Button
    var correctAnswer = 0
    var userScore = 0
    var userLife = 3

    lateinit var timer: CountDownTimer
    private val startTimeInMillies: Long = 10000
    var timeLeft: Long = startTimeInMillies


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        supportActionBar!!.title = "ADDITION"

        textScore = findViewById(R.id.score)
        textLife = findViewById(R.id.life)
        textTime = findViewById(R.id.time)
        textQuestion = findViewById(R.id.questionText)
        editTextAnswer = findViewById(R.id.answerText)
        buttonOk = findViewById(R.id.ok)
        buttonNext = findViewById(R.id.next)

        gameContinue()

        buttonOk.setOnClickListener{
            val input = editTextAnswer.text.toString()

            if (input == ""){
                Toast.makeText(this@GameActivity,"Please write an answer or click the next button",Toast.LENGTH_LONG).show()
            } else {
                pauseTimer()
                val userAnswer = input.toInt()

                if(userAnswer == correctAnswer) {
                    userScore += 10
                    textQuestion.text = "Congrats correct answer"
                    textScore.text = userScore.toString()
                } else {
                    userLife--
                    textQuestion.text = "Sorry your answer is wrong"
                    textLife.text = userLife.toString()
                }
            }
        }

        buttonNext.setOnClickListener {
            pauseTimer()
            resetTimer()
            editTextAnswer.setText("")

            if (userLife == 0) {
                val intent = Intent(this@GameActivity, ResultActivity::class.java)
                intent.putExtra("score", userScore)
                startActivity(intent)
                finish()
                Toast.makeText(applicationContext,"Game Over",Toast.LENGTH_LONG).show()
            } else {
                gameContinue()
            }
        }
    }

    fun gameContinue() {
        var number1 = Random.nextInt(0,100)
        var number2 = Random.nextInt(0,100)
        textQuestion.text = "$number1 + $number2"

        correctAnswer = number1 + number2
        startTimer()
    }

    fun startTimer() {
        timer = object : CountDownTimer(timeLeft,1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()
                userLife--
                textLife.text = userLife.toString()
                textQuestion.text = "Sorry, time is up"
            }
        }.start()
    }
    fun updateText() {
        val remainingTime: Int = (timeLeft / 1000).toInt()
        textTime.text = String.format(Locale.getDefault(),"%02d", remainingTime)
    }
    fun pauseTimer() {
        timer.cancel()
    }
    fun resetTimer() {
        timeLeft = startTimeInMillies
        updateText()
    }
}