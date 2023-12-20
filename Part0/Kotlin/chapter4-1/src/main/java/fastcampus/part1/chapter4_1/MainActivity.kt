package fastcampus.part1.chapter4_1

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
    var result: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edit1 = findViewById<EditText>(R.id.Edit1)
        edit2 = findViewById<EditText>(R.id.Edit2)
        btnAdd = findViewById<Button>(R.id.BtnAdd)
        btnSub = findViewById<Button>(R.id.BtnSub)
        btnMul = findViewById<Button>(R.id.BtnMul)
        btnDiv = findViewById<Button>(R.id.BtnDiv)
        btnRem = findViewById<Button>(R.id.BtnRem)
        textResult = findViewById<TextView>(R.id.TextResult)

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
        btnRem.setOnClickListener {
            num1 = edit1.text.toString()
            num2 = edit2.text.toString()
            if (inputFirst(num1, num2)) {
                result = num1.toDouble() % num2.toDouble()
                textResult.text = "계산 결과: " + result.toString()
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