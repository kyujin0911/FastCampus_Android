package fastcampus.part1.chapter5_3

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        var params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        var baseLayout = LinearLayout(this)
        baseLayout.orientation = LinearLayout.VERTICAL
        setContentView(baseLayout, params)

        var edit = EditText(this)
        baseLayout.addView(edit)
        var button = Button(this)
        button.text = "버튼입니다"
        button.setBackgroundColor(Color.YELLOW)
        baseLayout.addView(button)
        var text = TextView(this)
        text.setTextColor(Color.MAGENTA)
        text.setTextSize(30F)
        baseLayout.addView(text)

        button.setOnClickListener {
            text.text = edit.text.toString()
        }
    }
}