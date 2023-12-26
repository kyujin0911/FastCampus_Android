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
    private val rectWidth = 10f
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

        ampList.add(maxAmplitude)
        rectList.clear() // 초기화화

        //val maxRect = (this.width /rectWidth).toInt()

        val amps = ampList.takeLast(maxRect)

        for ((i, amp) in amps.withIndex()){
            val rectF = RectF()
            rectF.top = 0f
            rectF.bottom = amp
            rectF.left = i * rectWidth
            rectF.right = rectF.left + rectWidth
            rectList.add(rectF)
        }

        invalidate()
    }

    fun replayAmplitude(duration: Int){
        rectList.clear()

        //val maxRect = (this.width / rectWidth)
        val amps = ampList.take(tick).takeLast(maxRect)

        for ((i, amp) in amps.withIndex()){
            val rectF = RectF()
            rectF.top = 0f
            rectF.bottom = amp
            rectF.left = i * rectWidth
            rectF.right = rectF.left + rectWidth
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