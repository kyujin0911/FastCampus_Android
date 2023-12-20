package fastcampus.part1.chapter5_6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    lateinit var changeBtn: Button
    lateinit var img1 : ImageView
    lateinit var img2 : ImageView
    lateinit var img3 : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeBtn = findViewById<Button>(R.id.changeBtn)
        img1 = findViewById<ImageView>(R.id.img1)
        img2 = findViewById<ImageView>(R.id.img2)
        img3 = findViewById<ImageView>(R.id.img3)
        var count = 1
        changeBtn.setOnClickListener {
            if(count % 3 == 0){
                img1.visibility = View.INVISIBLE
                img2.visibility = View.VISIBLE
                img3.visibility = View.INVISIBLE
            }
            else if(count % 3 == 1){
                img1.visibility = View.INVISIBLE
                img2.visibility = View.INVISIBLE
                img3.visibility = View.VISIBLE
            }
            else{
                img1.visibility = View.VISIBLE
                img2.visibility = View.INVISIBLE
                img3.visibility = View.INVISIBLE
            }
            count = count+1
        }
    }
}