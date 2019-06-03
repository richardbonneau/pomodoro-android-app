package com.example.pomodoro

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        startWorkingButton.setOnClickListener{
            val selectedTime:String = spinner.selectedItem.toString()
            val intent:Intent = Intent(applicationContext, Pomodoro::class.java)
            intent.putExtra("timerLength",selectedTime)
            startActivity(intent)
        }
    }
}
