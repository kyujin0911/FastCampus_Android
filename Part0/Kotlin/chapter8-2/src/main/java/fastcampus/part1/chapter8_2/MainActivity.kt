package fastcampus.part1.chapter8_2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileInputStream

@Suppress("deprecation")
class MainActivity : AppCompatActivity() {

    lateinit var btnPrev : Button
    lateinit var btnNext : Button
    lateinit var myPicture : myPictureView
    var curNum : Int = 0
    var imageFiles : Array<File>? = null
    lateinit var imageFname : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "간단 이미지 뷰어"
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), Context.MODE_PRIVATE)

        btnPrev = findViewById<Button>(R.id.btnPrev)
        btnNext = findViewById<Button>(R.id.btnNext)
        myPicture = findViewById<myPictureView>(R.id.myPictureView1)

        imageFiles = File(Environment.getExternalStorageDirectory().absolutePath + "/Pictures").listFiles()
        imageFname = imageFiles!![0].toString()
        myPicture.imagePath=imageFname

        btnPrev.setOnClickListener {
            if (curNum <= 0) {
                Toast.makeText(applicationContext, "첫번째 그림입니다", Toast.LENGTH_SHORT).show()
            } else {
                curNum--
                imageFname = imageFiles!![curNum].toString()
                myPicture.imagePath=imageFname
                myPicture.invalidate()
            }
        }

        btnNext.setOnClickListener {
            if (curNum >= imageFiles!!.size - 1) {
                Toast.makeText(applicationContext, "마지막 그림입니다", Toast.LENGTH_SHORT).show()
            } else {
                curNum++
                imageFname = imageFiles!![curNum].toString()
                myPicture.imagePath=imageFname
                myPicture.invalidate()
            }
        }
    }
}