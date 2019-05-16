package com.example.pomodoro

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var currentStep = "firstStep"
        var stepToGoToAfterBreak = "secondStep"
        var msUntilFinished = 3000L


        val mainTimer = object : CountDownTimer(7200000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                var seconds = (millisUntilFinished / 1000 % 60).toString()
                var minutes = (millisUntilFinished / (1000 * 60) % 60).toString()
                var hours = (millisUntilFinished / (1000 * 60 * 60) % 24).toString()

                if (seconds.length !== 2) seconds = "0" + seconds
                if (minutes.length !== 2) minutes = "0" + minutes
                if (hours.length !== 2) hours = "0" + hours
                mainTimerDisplay.text = hours + ":" + minutes + ":" + seconds

            }

            override fun onFinish() {
                firstCheckBox.isChecked = true
                println("finished")
            }
        }

        val secondaryTimer = object : CountDownTimer(msUntilFinished, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                var seconds = (millisUntilFinished / 1000 % 60).toString()
                var minutes = (millisUntilFinished / (1000 * 60) % 60).toString()
                var hours = (millisUntilFinished / (1000 * 60 * 60) % 24).toString()

                if (seconds.length !== 2) seconds = "0" + seconds
                if (minutes.length !== 2) minutes = "0" + minutes
                if (hours.length !== 2) hours = "0" + hours

                secondaryTimerDisplay.text = hours + ":" + minutes + ":" + seconds

            }

            override fun onFinish() {
                println("currentStep"+currentStep)
                println("stepToGoToAfterBreak"+stepToGoToAfterBreak)
                when (currentStep) {

                    "firstStep" -> {
                        stepToGoToAfterBreak = "secondStep"
                        currentStep = "fiveMinBreak"
                        firstCheckBox.isChecked = true
                        msUntilFinished = 5000L
                        this.start()

                    }
                    "secondStep" -> {
                        stepToGoToAfterBreak = "thirdStep"
                        currentStep = "fiveMinBreak"
                        secondCheckBox.isChecked = true
                        msUntilFinished = 5000L
                        this.start()
                    }
                    "thirdStep" -> {
                        stepToGoToAfterBreak = "fourthStep"
                        currentStep = "fiveMinBreak"
                        thirdCheckBox.isChecked = true
                        msUntilFinished = 5000L
                        this.start()
                    }
                    "fourthStep" -> {
                        stepToGoToAfterBreak = "firstStep"
                        currentStep = "fiveMinBreak"
                        fourthCheckBox.isChecked = true
                        msUntilFinished = 8000L
                        this.start()
                    }
                    "fiveMinBreak" -> {
                        currentStep = stepToGoToAfterBreak
                        msUntilFinished = 2000L
                        this.start()
                    }

                }

            }
        }

        mainTimer.start()
        secondaryTimer.start()
    }
}


