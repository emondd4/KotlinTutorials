package com.charityright.customprogressbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.os.CountDownTimer
import android.view.View


class MainActivity : AppCompatActivity() {

    private var CurrentProgress = 0
    private lateinit var progressBar: ProgressBar
    private lateinit var StartProgress: Button
    private var countDownTimer: CountDownTimer? = null

    private var TimerRunning = false
    private var START_TIME_IN_MILLIS: Long = 0

    private var TimeLeftMillis: Long = 0
    private var i = 0
    private var imma: Long = 10000
    private var flag = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)
        StartProgress = findViewById(R.id.startProgess)

        var countDownTimer: CountDownTimer = object : CountDownTimer(0 * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                progressBar.progress = CurrentProgress
                progressBar.max = 100
                CurrentProgress += 10
            }

            override fun onFinish() {}
        }


        StartProgress.setOnClickListener {
            startTimer()
        }

    }

    private fun startTimer() {
        if (flag == 0) {
            imma = 10000
            START_TIME_IN_MILLIS = imma
            TimeLeftMillis = imma
            flag = 1
        }
        progressBar.progress = i
        countDownTimer = object : CountDownTimer(TimeLeftMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                TimeLeftMillis = millisUntilFinished
                i++
                progressBar.progress = (i * 100 / (imma  / 1000)).toInt()
            }

            override fun onFinish() {
                i++
                progressBar.progress = 100
            }
        }.start()
    }
}