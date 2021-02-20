package com.emon.workmanager


import android.content.Context
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.logging.Handler


class SampleClass(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    private val TAG = "SampleClass"

    override fun doWork(): Result {
        doSomething()
        return Result.success()
    }

    private fun doSomething() {

        Thread(Runnable {
            for (i in 0..20) {
                Log.d(TAG, "run: $i")
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            Log.d(TAG, "Job finished")
        }).start()
    }
}
