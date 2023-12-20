package fastcampus.part1.chapter4_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var text1: TextView
    lateinit var text2: TextView
    lateinit var startswitch: Switch
    lateinit var rGroup1: RadioGroup
    lateinit var rdoOreo: RadioButton
    lateinit var rdoPie: RadioButton
    lateinit var rdoQ: RadioButton
    lateinit var imgandroid: ImageView
    lateinit var finishBtn: Button
    lateinit var resetBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "안드로이드 사진 보기"

        text1 = findViewById<TextView>(R.id.Text1)
        startswitch = findViewById<Switch>(R.id.startSwitch)

        text2 = findViewById<TextView>(R.id.Text2)
        rGroup1 = findViewById<RadioGroup>(R.id.Rgroup1)
        rdoOreo = findViewById<RadioButton>(R.id.RdoOreo)
        rdoPie = findViewById<RadioButton>(R.id.RdoPie)
        rdoQ = findViewById<RadioButton>(R.id.RdoQ)

        imgandroid = findViewById<ImageView>(R.id.ImgAndroid)
        finishBtn = findViewById<Button>(R.id.finishBtn)
        resetBtn = findViewById<Button>(R.id.resetBtn)

        startswitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(startswitch.isChecked){
                text2.visibility = View.VISIBLE
                rGroup1.visibility = View.VISIBLE
                finishBtn.visibility = View.VISIBLE
                resetBtn.visibility = View.VISIBLE
                imgandroid.visibility = View.VISIBLE
            }
            else{
                text2.visibility = View.INVISIBLE
                rGroup1.visibility = View.INVISIBLE
                finishBtn.visibility = View.INVISIBLE
                resetBtn.visibility = View.INVISIBLE
                imgandroid.visibility = View.INVISIBLE
            }
        }
        rdoOreo.setOnClickListener {
            if(rdoOreo.isChecked)
                imgandroid.setImageResource(R.drawable.oreo)
        }
        rdoPie.setOnClickListener {
            if(rdoPie.isChecked)
                imgandroid.setImageResource(R.drawable.pi)
        }
        rdoQ.setOnClickListener {
            if(rdoQ.isChecked)
                imgandroid.setImageResource(R.drawable.q)
        }
        finishBtn.setOnClickListener {
            finish()
        }
        resetBtn.setOnClickListener {
            startswitch.toggle()
        }
    }
}