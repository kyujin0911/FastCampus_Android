package fastcampus.part1.chapter10_3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import fastcampus.part1.chapter10_3.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Second 액티비티"

        var inIntent = intent
        var operator = inIntent.getStringExtra("Operator")
        var num1 = inIntent.getIntExtra("Num1", 0)
        var num2 = inIntent.getIntExtra("Num2", 0)
        var resultValue: Int? = when (operator) {
            "+" -> {
                num1 + num2
            }
            "-" -> {
                num1 - num2
            }
            "*" -> {
                num1 * num2
            }
            else -> {
                num1 / num2
            }
        }
        var outIntent = Intent(applicationContext, MainActivity::class.java)
        outIntent.putExtra("Result", resultValue)
        setResult(Activity.RESULT_OK, outIntent)
        finish()
    }
}