package umc.mission.part2chapter2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.text.Typography.amp

class WaveformView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val ampList = mutableListOf<Float>()
    private val rectList = mutableListOf<RectF>()
    private val rectWidth = 15f
    private val maxRect = (this.width / rectWidth).toInt()
    private var tick = 0

    private val redPaint = Paint().apply {
        color = Color.RED
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        for(rectF in rectList){
            canvas?.drawRect(rectF, redPaint)
        }
    }

    fun addAmplitude(maxAmplitude: Float){
        val amplitude = (maxAmplitude / Short.MAX_VALUE) * this.height * 0.8f // 0~1 사이의 값으로 지정

        ampList.add(amplitude)
        rectList.clear() // 초기화화

        //val maxRect = (this.width /rectWidth).toInt()

        val amps = ampList.takeLast(maxRect)

        for ((i, amp) in amps.withIndex()){
            val rectF = RectF()
            rectF.top = (this.height / 2) - amp / 2 - 3f
            rectF.bottom = rectF.top + amp + 3f
            rectF.left = i * rectWidth
            rectF.right = rectF.left + rectWidth - 5f // 여백을 위해 5를 빼줌
            rectList.add(rectF)
        }

        invalidate()
    }

    fun replayAmplitude(){
        rectList.clear()

        //val maxRect = (this.width / rectWidth)
        val amps = ampList.take(tick).takeLast(maxRect)

        for ((i, amp) in amps.withIndex()){
            val rectF = RectF()
            rectF.top = (this.height / 2) - amp / 2 - 3f
            rectF.bottom = rectF.top + amp + 3f
            rectF.left = i * rectWidth
            rectF.right = rectF.left + rectWidth - 5f
            rectList.add(rectF)
        }

        tick++

        invalidate()
    }

    fun clearData(){
        ampList.clear()
    }

    fun clearWave(){
        rectList.clear()
        tick = 0
        invalidate()
    }
}