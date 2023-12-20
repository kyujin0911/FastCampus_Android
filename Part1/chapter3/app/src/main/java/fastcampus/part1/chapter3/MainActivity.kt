package fastcampus.part1.chapter3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView
import androidx.core.text.isDigitsOnly
import androidx.core.widget.addTextChangedListener
import fastcampus.part1.chapter3.databinding.ActivityMainBinding
import org.w3c.dom.Text
import kotlin.text.Typography.times
import kotlin.time.times

class MainActivity : AppCompatActivity() {
    var cmToM = true
    private lateinit var binding: ActivityMainBinding // lateinit시 반드시 타입을 지정해줘야함
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("onCreate", cmToM.toString())
        super.onCreate(savedInstanceState)
        binding =
            ActivityMainBinding.inflate(layoutInflater) // inflate: 불러일으키다, 화면을 만들겠다, Activity는 LayoutInflater를 소유
        val view = binding.root
        setContentView(view)

        //val outPutTextView = findViewById<TextView>(R.id.outputTextView)
        // viewBinding을 사용하는 이유 findViewById를 사용할 때 id의 중복을 방지하기 위해
        // 다른 xml에 선언한 id를 정확하게 참조하기 위해

        val outputTextView = binding.outputTextView
        val outputUnitTextView = binding.outputUnitTextView
        val inputEditText = binding.inputEditText
        val inputUnitTextView = binding.inputUnitTextView
        val swapImageButton = binding.swapImageButton
        var inputNumber: Int = 0

        inputEditText.addTextChangedListener { // 문자열 변경이 완료되면 알려주는 리스너
            /*text -> inputNumber = try{
                text.toString().toInt()
        }catch (e:NumberFormatException){
            0
        }*/
                text ->
            inputNumber = if (text.isNullOrEmpty()) {
                0
            } else {
                text.toString().toInt()
            }

            if (cmToM) {
                outputTextView.text = inputNumber.times(0.01).toString()
            } else {
                outputTextView.text = inputNumber.times(100).toString()
            }
            //Log.d("inputNumber", inputNumber.toString())
        }
        swapImageButton.setOnClickListener {
            cmToM = cmToM.not()
            Log.d("cmToM", cmToM.toString())
            if (cmToM) {
                inputUnitTextView.text = "cm"
                outputUnitTextView.text = "m"
                outputTextView.text = inputNumber.times(0.01).toString()
            } else {
                inputUnitTextView.text = "m"
                outputUnitTextView.text = "cm"
                outputTextView.text = inputNumber.times(100).toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("cmToM", cmToM)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        cmToM = savedInstanceState.getBoolean("cmToM")
        Log.d("cmToM", cmToM.toString())
        binding.inputUnitTextView.text = if (cmToM) "cm" else "m"
        binding.outputUnitTextView.text = if (cmToM) "m" else "cm"
        super.onRestoreInstanceState(savedInstanceState)
    }
}