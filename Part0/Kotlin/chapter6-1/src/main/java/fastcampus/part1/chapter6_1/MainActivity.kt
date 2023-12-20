package fastcampus.part1.chapter6_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Chronometer
import android.widget.DatePicker
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.TimePicker

class MainActivity : AppCompatActivity() {
    lateinit var chrono: Chronometer
    lateinit var rG: RadioGroup
    lateinit var rB1: RadioButton
    lateinit var rB2: RadioButton
    lateinit var timePicker: TimePicker
    lateinit var datePicker: DatePicker
    lateinit var year: TextView
    lateinit var month: TextView
    lateinit var hour: TextView
    lateinit var minute: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        chrono = findViewById<Chronometer>(R.id.chronometer1)
        rG = findViewById<RadioGroup>(R.id.rGroup)
        rB1 = findViewById<RadioButton>(R.id.rdoCal)
        rB2 = findViewById<RadioButton>(R.id.rdoTime)
        timePicker = findViewById<TimePicker>(R.id.timePicker)
        datePicker = findViewById<DatePicker>(R.id.datePicker)
        year = findViewById<TextView>(R.id.tvYear)
        month = findViewById<TextView>(R.id.tvMonth)
        hour = findViewById<TextView>(R.id.tvHour)
        minute = findViewById<TextView>(R.id.tvMinute)

        chrono.setOnClickListener {
            rG.visibility = View.VISIBLE

        }
    }
}