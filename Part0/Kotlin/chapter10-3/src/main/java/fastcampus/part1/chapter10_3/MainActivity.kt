package fastcampus.part1.chapter10_3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import fastcampus.part1.chapter10_3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "메인 액티비티"

        binding.calculateBtn.setOnClickListener {
            var intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("Num1", Integer.parseInt(binding.edtNum1.text.toString()))
            intent.putExtra("Num2", Integer.parseInt(binding.edtNum2.text.toString()))
            var operator = when {
                binding.RdoAdd.isChecked -> "+"
                binding.RdoSub.isChecked -> "-"
                binding.RdoMul.isChecked -> "*"
                else -> "/"
            }
            intent.putExtra("Operator", operator)
            startActivityForResult(intent, 0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            var hap = data!!.getIntExtra("Result", 0)
            Toast.makeText(applicationContext, "합계 :$hap", Toast.LENGTH_SHORT).show()
        }
    }
}