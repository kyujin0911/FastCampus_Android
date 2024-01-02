package umc.mission.part3chapter2.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.GridLayout
import android.widget.TextView
import androidx.core.view.children
import umc.mission.part3chapter2.databinding.WidgetShuffleNumberKeyboardBinding
import kotlin.random.Random

class ShuffleNumberKeyboard @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0):
    GridLayout(context, attrs, defStyleAttr) {
        private var _binding : WidgetShuffleNumberKeyboardBinding? = null
    private val binding get() = _binding!!

    init {
        _binding = WidgetShuffleNumberKeyboardBinding.inflate(LayoutInflater.from(context), this, true)
        shuffle()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        _binding = null
    }

    fun shuffle(){
        val keyNumberArray = ArrayList<String>()
        for (i in 0..9){
            keyNumberArray.add(i.toString())
        }

        binding.gridLayout.children.forEach { view ->
            if(view is TextView && view.tag != null){
                val randIndex = Random.nextInt(keyNumberArray.size)
                view.text = keyNumberArray[randIndex]
                keyNumberArray.removeAt(randIndex)
            }
        }
    }
}