package kr.ac.tukorea.recommend.practice1

import android.icu.util.UniversalTimeScale.toBigDecimal
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kr.ac.tukorea.recommend.practice1.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    var firstInputNumber: StringBuilder = StringBuilder("")
    var secondInputNumber: StringBuilder = StringBuilder("")
    var operatorText: StringBuilder = StringBuilder("")
    var decimalFormat = DecimalFormat("#,###")
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun numberClicked(view: View){
        val numberString = (view as? Button)?.text.toString() ?: ""
        val numberText = if (operatorText.isEmpty()) firstInputNumber else secondInputNumber

        numberText.append(numberString)
        updateEquationTextView()
    }

    fun clearClicked(view: View){
        firstInputNumber.clear()
        secondInputNumber.clear()
        operatorText.clear()

        binding.resultTextView.text = ""
        updateEquationTextView()
    }

    fun equalClicked(view: View){
        if (firstInputNumber.isEmpty() || secondInputNumber.isEmpty() || operatorText.isEmpty()){
            Toast.makeText(this, "올바르지 않은 수식입니다.", Toast.LENGTH_SHORT).show()
        }
        else{
            val result = when(operatorText.toString()){
                "+" -> firstInputNumber.toString().toBigDecimal() + secondInputNumber.toString().toBigDecimal()
                "-" -> firstInputNumber.toString().toBigDecimal() - secondInputNumber.toString().toBigDecimal()
                else -> "잘못된 수식입니다."
            }.toString()
            binding.resultTextView.text = decimalFormat.format(result.toBigDecimal()).toString()
        }
    }

    fun operatorClicked(view: View){
        val operatorString = (view as? Button)?.text.toString() ?: ""
        if (firstInputNumber.isEmpty()){
            Toast.makeText(this, "숫자를 먼저 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        if(secondInputNumber.isNotEmpty()){
            Toast.makeText(this, "한 개의 연산자에 대한 연산만 가능합니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (operatorText.isNotEmpty()){
            Toast.makeText(this, "잘못된 수식입니다.", Toast.LENGTH_SHORT).show()
            return
        }
        operatorText.append(operatorString)
        updateEquationTextView()
    }

    private fun updateEquationTextView(){
        val firstNumber = if(firstInputNumber.isNotEmpty())decimalFormat.format(firstInputNumber
            .toString().toBigDecimal()) else ""
        val secondNumber = if(secondInputNumber.isNotEmpty())decimalFormat.format(secondInputNumber
            .toString().toBigDecimal()) else ""
        binding.equationTextView.text = "$firstNumber$operatorText$secondNumber"
    }
}