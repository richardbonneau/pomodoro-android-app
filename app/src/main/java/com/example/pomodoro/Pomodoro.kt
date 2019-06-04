
package com.example.pomodoro

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_pomodoro.*


class Pomodoro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pomodoro)

        val timerLength:Long = intent.getStringExtra("timerLength").substring(0,1).toLong()*3600000


        var workForHowMuchTime = timerLength
        var currentStep = "firstStep"
        var stepToGoToAfterBreak = ""
        var howMuchTimeIsNextStep = 0L
        var msUntilNextStep = 25000L


        fun moveToNextStep(): String {

            when (currentStep) {
                "firstStep" -> {
                    println("currentstep is 1ststep")
                    msUntilNextStep = 5000
                    stepToGoToAfterBreak = "secondStep"
                    howMuchTimeIsNextStep = 25000

//                    debug.text = "fiveMinBreak"
                    return "fiveMinBreak"
                }
                "secondStep" -> {
                    msUntilNextStep = 5000L
                    stepToGoToAfterBreak = "thirdStep"
                    howMuchTimeIsNextStep = 25000

//                    debug.text = "fiveMinBreak"
                    return "fiveMinBreak"
                }
                "thirdStep" -> {
                    msUntilNextStep = 5000L
                    stepToGoToAfterBreak = "fourthStep"
                    howMuchTimeIsNextStep = 25000

//                    debug.text = "fiveMinBreak"
                    return "fiveMinBreak"
                }
                "fourthStep"-> {
                    msUntilNextStep = 5000L
                    stepToGoToAfterBreak = "firstStep"
                    howMuchTimeIsNextStep = 25000

//                    debug.text = "Thirty min Break"
                    return "thirtyMinBreak"
                }
                "fiveMinBreak" -> {
                    msUntilNextStep = howMuchTimeIsNextStep
//                    debug.text = stepToGoToAfterBreak
                    return stepToGoToAfterBreak
                }
                "thirtyMinBreak" -> {
                    msUntilNextStep = howMuchTimeIsNextStep
//                    debug.text = stepToGoToAfterBreak
                    return stepToGoToAfterBreak
                }
                else ->return currentStep

            }
        }

        val mainTimer = object : CountDownTimer(workForHowMuchTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                msUntilNextStep -= 1000

                if (msUntilNextStep < 1000) currentStep = moveToNextStep()


                // Main Timer
                var mainTimerSeconds = (millisUntilFinished / 1000 % 60).toString()
                var mainTimerMinutes = (millisUntilFinished / (1000 * 60) % 60).toString()
                var mainTimerHours = (millisUntilFinished / (1000 * 60 * 60) % 24).toString()

                if (mainTimerSeconds.length !== 2) mainTimerSeconds = "0" + mainTimerSeconds
                if (mainTimerMinutes.length !== 2) mainTimerMinutes = "0" + mainTimerMinutes
                if (mainTimerHours.length !== 2) mainTimerHours = "0" + mainTimerHours
                mainTimerDisplay.text = mainTimerHours + ":" + mainTimerMinutes + ":" + mainTimerSeconds

                //Secondary Timer

                var secondaryTimerSeconds = (msUntilNextStep / 1000 % 60).toString()
                var secondaryTimerMinutes = (msUntilNextStep / (1000 * 60) % 60).toString()
                var secondaryTimerHours = (msUntilNextStep / (1000 * 60 * 60) % 24).toString()

                if (secondaryTimerSeconds.length !== 2) secondaryTimerSeconds = "0" + secondaryTimerSeconds
                if (secondaryTimerMinutes.length !== 2) secondaryTimerMinutes = "0" + secondaryTimerMinutes
                if (secondaryTimerHours.length !== 2) secondaryTimerHours = "0" + secondaryTimerHours
                secondaryTimerDisplay.text = secondaryTimerHours + ":" + secondaryTimerMinutes + ":" + secondaryTimerSeconds



            }

            override fun onFinish() {

                println("finished")
            }
        }


//        val secondaryTimer = object : CountDownTimer(msUntilFinished, 1000) {
//            override fun onTick(millisUntilFinished: Long) {
//
//                var mainTimerSeconds = (millisUntilFinished / 1000 % 60).toString()
//                var minutes = (millisUntilFinished / (1000 * 60) % 60).toString()
//                var hours = (millisUntilFinished / (1000 * 60 * 60) % 24).toString()
//
//                if (seconds.length !== 2) seconds = "0" + seconds
//                if (minutes.length !== 2) minutes = "0" + minutes
//                if (hours.length !== 2) hours = "0" + hours
//
//                secondaryTimerDisplay.text = hours + ":" + minutes + ":" + seconds
//
//            }
//
//            override fun onFinish() {
//                println("currentStep"+currentStep)
//                println("stepToGoToAfterBreak"+stepToGoToAfterBreak)
//                when (currentStep) {
//
//                    "firstStep" -> {
//                        stepToGoToAfterBreak = "secondStep"
//                        currentStep = "fiveMinBreak"
//                        firstCheckBox.isChecked = true
//                        msUntilFinished = 5000L
//                        this.start()
//
//                    }
//                    "secondStep" -> {
//                        stepToGoToAfterBreak = "thirdStep"
//                        currentStep = "fiveMinBreak"
//                        secondCheckBox.isChecked = true
//                        msUntilFinished = 5000L
//                        this.start()
//                    }
//                    "thirdStep" -> {
//                        stepToGoToAfterBreak = "fourthStep"
//                        currentStep = "fiveMinBreak"
//                        thirdCheckBox.isChecked = true
//                        msUntilFinished = 5000L
//                        this.start()
//                    }
//                    "fourthStep" -> {
//                        stepToGoToAfterBreak = "firstStep"
//                        currentStep = "fiveMinBreak"
//                        fourthCheckBox.isChecked = true
//                        msUntilFinished = 8000L
//                        this.start()
//                    }
//                    "fiveMinBreak" -> {
//                        currentStep = stepToGoToAfterBreak
//                        msUntilFinished = 2000L
//                        this.start()
//                    }
//
//                }
//
//            }
//        }

        mainTimer.start()

    }
}



