package fastcampus.part1.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import fastcampus.part1.practice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var firstNumberText = StringBuilder("")
    private var secondNumberText = StringBuilder("")
    private var operatorText = StringBuilder("")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun numberClicked(view: View){
        val numberString = ""
        val numberText = (view as? Button)?.text
        if()
    }

    fun clearClicked(view: View){

    }

    fun equalClicked(view: View){

    }

    fun operatorClicked(view: View){

    }
}