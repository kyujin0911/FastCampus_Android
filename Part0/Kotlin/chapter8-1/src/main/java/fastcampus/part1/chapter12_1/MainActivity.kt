package fastcampus.part1.chapter12_1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import fastcampus.part1.chapter12_1.databinding.ActivityMainBinding
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var fileName: String
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "간단 일기장"

        var cal = Calendar.getInstance()
        var cYear = cal.get(Calendar.YEAR)
        var cMonth = cal.get(Calendar.MONTH)
        var cDay = cal.get(Calendar.DAY_OF_MONTH)

        binding.datePicker1.init(cYear, cMonth, cDay) { view, year, monthOfYear, dayOfMonth ->
            fileName = (Integer.toString(year) + "_"
                    + Integer.toString(monthOfYear + 1) + "_"
                    + Integer.toString(dayOfMonth) + ".txt")
            var str = readDiary(fileName)
            binding.edtDiary.setText(str)
            binding.btnWrite.isEnabled = true
        }

        binding.btnWrite.setOnClickListener {
            var outFs = openFileOutput(fileName, Context.MODE_PRIVATE)
            var str = binding.edtDiary.text.toString()
            outFs.write(str.toByteArray())
            outFs.close()
            Toast.makeText(applicationContext, "$fileName 이 저장됨", Toast.LENGTH_SHORT).show()
        }
    }

    fun readDiary(fName: String): String? {
        var diaryStr: String? = null
        var inFs: FileInputStream
        try {
            inFs = openFileInput(fName)
            var txt = ByteArray(500)
            inFs.read(txt)
            inFs.close()
            diaryStr = txt.toString(Charsets.UTF_8).trim()
            binding.btnWrite.text = "수정 하기"
        } catch (e: IOException) {
            binding.edtDiary.hint = "일기 없음"
            binding.btnWrite.text = "새로 저장"
        }
        return diaryStr
    }

    override fun onResume() {
        var cal = Calendar.getInstance()
        var cYear = cal.get(Calendar.YEAR)
        var cMonth = cal.get(Calendar.MONTH)
        var cDay = cal.get(Calendar.DAY_OF_MONTH)
        fileName = cYear.toString() + "_" + (cMonth+1).toString() + "_" + cDay.toString() + ".txt"
        val str = readDiary(fileName)
        binding.edtDiary.setText(str)
        binding.btnWrite.isEnabled = true
        Log.d("fileName", "$fileName")
        super.onResume()
    }

}