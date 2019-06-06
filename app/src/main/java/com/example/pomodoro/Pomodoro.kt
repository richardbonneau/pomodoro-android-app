
package com.example.pomodoro

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_pomodoro.*


class Pomodoro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pomodoro)




        //Value coming from the MainActivty view.
        val timerLength:Long = intent.getStringExtra("timerLength").substring(0,1).toLong()*3600000

        var currentStep = "firstStep"
        var stepToGoToAfterBreak = ""
        var howMuchTimeIsNextStep = 0L
        var msUntilCurrentStepIsDone = 25000L


        fun moveToNextStep(): String {
            when (currentStep) {
                "firstStep" -> {
                    println("currentstep is 1ststep")
                    msUntilCurrentStepIsDone = 5000
                    stepToGoToAfterBreak = "secondStep"
                    howMuchTimeIsNextStep = 25000
                    return "fiveMinBreak"
                }
                "secondStep" -> {
                    msUntilCurrentStepIsDone = 5000L
                    stepToGoToAfterBreak = "thirdStep"
                    howMuchTimeIsNextStep = 25000
                    return "fiveMinBreak"
                }
                "thirdStep" -> {
                    msUntilCurrentStepIsDone = 5000L
                    stepToGoToAfterBreak = "fourthStep"
                    howMuchTimeIsNextStep = 25000

                    return "fiveMinBreak"
                }
                "fourthStep"-> {
                    msUntilCurrentStepIsDone = 5000L
                    stepToGoToAfterBreak = "firstStep"
                    howMuchTimeIsNextStep = 25000
                    return "thirtyMinBreak"
                }
                "fiveMinBreak" -> {
                    msUntilCurrentStepIsDone = howMuchTimeIsNextStep
                    return stepToGoToAfterBreak
                }
                "thirtyMinBreak" -> {
                    msUntilCurrentStepIsDone = howMuchTimeIsNextStep
                    return stepToGoToAfterBreak
                }
                else ->return currentStep
            }
        }

        val mainTimer = object : CountDownTimer(timerLength, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                msUntilCurrentStepIsDone -= 1000
                if (msUntilCurrentStepIsDone < 1000) currentStep = moveToNextStep()

                // Main Timer
                var mainTimerSeconds = (millisUntilFinished / 1000 % 60).toString()
                var mainTimerMinutes = (millisUntilFinished / (1000 * 60) % 60).toString()
                var mainTimerHours = (millisUntilFinished / (1000 * 60 * 60) % 24).toString()

                // Is there a clearner way to add that 0?
                if (mainTimerSeconds.length !== 2) mainTimerSeconds = "0" + mainTimerSeconds
                if (mainTimerMinutes.length !== 2) mainTimerMinutes = "0" + mainTimerMinutes
                if (mainTimerHours.length !== 2) mainTimerHours = "0" + mainTimerHours
                mainTimerDisplay.text = mainTimerHours + ":" + mainTimerMinutes + ":" + mainTimerSeconds

                //Secondary Timer

                var secondaryTimerSeconds = (msUntilCurrentStepIsDone / 1000 % 60).toString()
                var secondaryTimerMinutes = (msUntilCurrentStepIsDone / (1000 * 60) % 60).toString()
                var secondaryTimerHours = (msUntilCurrentStepIsDone / (1000 * 60 * 60) % 24).toString()

                // Is there a clearner way to add that 0?
                if (secondaryTimerSeconds.length !== 2) secondaryTimerSeconds = "0" + secondaryTimerSeconds
                if (secondaryTimerMinutes.length !== 2) secondaryTimerMinutes = "0" + secondaryTimerMinutes
                if (secondaryTimerHours.length !== 2) secondaryTimerHours = "0" + secondaryTimerHours
                secondaryTimerDisplay.text = secondaryTimerHours + ":" + secondaryTimerMinutes + ":" + secondaryTimerSeconds
            }

            override fun onFinish() {
                println("finished")
            }
        }
        mainTimer.start()

//        val newTextView = TextView(this)
//        newTextView.textSize = 20f
//        newTextView.text = "hello there"
//
//
//        listOfTasks.addView(newText
    }
}



