package fastcampus.part1.chapter7_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import java.lang.Float

class MainActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var editText: EditText
    lateinit var resetBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById<ImageView>(R.id.iv)
        editText = findViewById<EditText>(R.id.editText)
        resetBtn = findViewById<Button>(R.id.resetBtn)

        resetBtn.setOnClickListener {
            imageView.rotation = 0.0f
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.turnDraw -> imageView.rotation = editText.text.toString().toFloat()
            R.id.item1 -> imageView.setImageResource(R.drawable.jeju1)
            R.id.item2 -> imageView.setImageResource(R.drawable.jeju3)
            R.id.item3 -> imageView.setImageResource(R.drawable.jeju4)
        }
        return super.onOptionsItemSelected(item)
    }
}