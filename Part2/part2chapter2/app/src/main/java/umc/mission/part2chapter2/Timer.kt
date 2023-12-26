package umc.mission.part2chapter2

import android.os.Looper
import java.time.Duration
import java.util.logging.Handler

class Timer(listener: OnTimerTickListener) {
    private var duration = 0L
    private val handler = android.os.Handler(Looper.getMainLooper())
    private val runnable: Runnable = object: Runnable{
        override fun run() {
            duration += 100L
            handler.postDelayed(this, 100L) // handler에 delay와 함께 작업을 실행시킴
            listener.onTick(duration)
        }
    }

    fun start(){
        handler.postDelayed(runnable, 100L)
    }

    fun stop(){
        handler.removeCallbacks(runnable)
    }
}

interface OnTimerTickListener{
    fun onTick(duration: Long)
}