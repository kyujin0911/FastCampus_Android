package fastcampus.part1.chapter10_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton

class MainActivity : AppCompatActivity() {
    lateinit var secondBtn : RadioButton
    lateinit var thirdBtn : RadioButton
    lateinit var newAcitvityBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        secondBtn = findViewById<RadioButton>(R.id.secondRBtn)
        thirdBtn = findViewById<RadioButton>(R.id.thirddRBtn)
        newAcitvityBtn = findViewById<Button>(R.id.newActivityBtn)

        var intent: Intent?
        newAcitvityBtn.setOnClickListener {
            if(secondBtn.isChecked){
                intent = Intent(applicationContext, SecondActivity::class.java)
                startActivity(intent)
            }
            else{
                intent = Intent(applicationContext, ThirdActivity::class.java)
                startActivity(intent)
            }
        }
    }
}