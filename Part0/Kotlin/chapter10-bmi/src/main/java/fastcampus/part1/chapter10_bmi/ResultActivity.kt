package fastcampus.part1.chapter10_bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fastcampus.part1.chapter10_bmi.databinding.ActivityResultBinding
import kotlin.math.pow

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val height = intent.getStringExtra("height")!!.toInt()
        val weight = intent.getStringExtra("weight")!!.toInt()

        val bmi = weight / (height / 100.0).pow(2.0)
        when{
            bmi >= 35 -> binding.ResultTextView.text = "고도 비만"
            bmi >= 30 -> binding.ResultTextView.text = "2단계 비만"
            bmi >= 25 -> binding.ResultTextView.text = "1단계 비만"
            bmi >= 23 -> binding.ResultTextView.text = "과체중"
            bmi >= 18.5 -> binding.ResultTextView.text = "정상"
            else -> binding.ResultTextView.text = "저체중"
        }

        when{
            bmi >= 23 ->
                binding.imageView.setImageResource(R.drawable.baseline_sentiment_very_dissatisfied_24)
            bmi >= 18.5 ->
                binding.imageView.setImageResource(R.drawable.baseline_sentiment_satisfied_alt_24)
            else ->
                binding.imageView.setImageResource(R.drawable.baseline_sentiment_dissatisfied_24)
        }

        Toast.makeText(this, "$bmi", Toast.LENGTH_SHORT).show()
    }
}