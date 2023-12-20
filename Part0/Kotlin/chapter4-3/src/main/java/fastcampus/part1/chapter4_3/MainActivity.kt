package fastcampus.part1.chapter4_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.ACTION_DOWN
import android.view.KeyEvent.keyCodeToString
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var editText1: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText1 = findViewById<EditText>(R.id.editText)
        editText1.inputType = 0

        editText1.setOnKeyListener { v, keyCode, event ->
            if(event.action == ACTION_DOWN){
                Toast.makeText(this, "${editText1.text}", Toast.LENGTH_SHORT).show()
                true
            }
            false
        }
    }
}
