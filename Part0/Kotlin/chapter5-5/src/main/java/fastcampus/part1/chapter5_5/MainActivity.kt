package fastcampus.part1.chapter5_5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var edit1: EditText
    lateinit var edit2: EditText
    lateinit var btnAdd: Button
    lateinit var btnSub: Button
    lateinit var btnMul: Button
    lateinit var btnDiv: Button
    lateinit var btnRem: Button
    lateinit var textResult: TextView
    lateinit var num1: String
    lateinit var num2: String
    var numButtons = ArrayList<Button>(10)
    var numBtnIds = arrayOf(
        R.id.zero, R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six,
        R.id.seven, R.id.eight, R.id.nine
    )
    internal val i: Int = 0
    var result: Double? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edit1 = findViewById<EditText>(R.id.edit1)
        edit2 = findViewById<EditText>(R.id.edit2)
        btnAdd = findViewById<Button>(R.id.plus)
        btnSub = findViewById<Button>(R.id.minus)
        btnMul = findViewById<Button>(R.id.mul)
        btnDiv = findViewById<Button>(R.id.div)
        textResult = findViewById<TextView>(R.id.resultTextView)

        for (i in 0..9) {
            numButtons.add(findViewById<Button>(numBtnIds[i]))
        }

        for (i in 0..numBtnIds.size - 1) {
            numButtons[i].setOnClickListener {
                if (edit1.isFocused) {
                    num1 = edit1.text.toString() + numButtons[i].text.toString()
                    edit1.setText(num1)
                } else if (edit2.isFocused) {
                    num2 = edit2.text.toString() + numButtons[i].text.toString()
                    edit2.setText(num2)
                } else {
                    Toast.makeText(this, "먼저 EditText를 선택하세요", Toast.LENGTH_SHORT).show()
                }
            }


            btnAdd.setOnClickListener {
                num1 = edit1.text.toString()
                num2 = edit2.text.toString()
                if (inputFirst(num1, num2)) {
                    result = num1.toDouble() + num2.toDouble()
                    textResult.text = "계산 결과: " + result.toString()
                }
            }
            btnSub.setOnClickListener {
                num1 = edit1.text.toString()
                num2 = edit2.text.toString()
                if (inputFirst(num1, num2)) {
                    result = num1.toDouble() - num2.toDouble()
                    textResult.text = "계산 결과: " + result.toString()
                }
            }
            btnMul.setOnClickListener {
                num1 = edit1.text.toString()
                num2 = edit2.text.toString()
                if (inputFirst(num1, num2)) {
                    result = num1.toDouble() * num2.toDouble()
                    textResult.text = "계산 결과: " + result.toString()
                }

            }
            btnDiv.setOnClickListener {
                num1 = edit1.text.toString()
                num2 = edit2.text.toString()
                if (inputFirst(num1, num2)) {
                    if (num2 == "0") {
                        Toast.makeText(this, "0으로 나눌 수 없음", Toast.LENGTH_SHORT).show()
                    } else {
                        result = num1.toDouble() / num2.toDouble()
                        textResult.text = "계산 결과: " + result.toString()
                    }
                }
            }
        }
    }
    private fun inputFirst(num1: String, num2: String): Boolean {
        if (num1.isNullOrEmpty() || num2.isNullOrEmpty()) {
            Toast.makeText(this, "숫자를 입력하세요.", Toast.LENGTH_SHORT).show()
            return false
        } else {
            return true
        }
    }
}