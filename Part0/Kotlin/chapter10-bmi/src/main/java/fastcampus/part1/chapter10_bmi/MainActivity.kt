package fastcampus.part1.chapter10_bmi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceManager
import fastcampus.part1.chapter10_bmi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        binding.resultButton.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            saveData(binding.heightEditText.text.toString().toInt(), binding.weightEditText.text.toString().toInt())
            intent.putExtra("weight", binding.weightEditText.text.toString())
            intent.putExtra("height", binding.heightEditText.text.toString())
            startActivity(intent)
        }
    }

    private fun saveData(height: Int, weight: Int){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putInt("KEY_HEIGHT", height)
            .putInt("KEY_WEIGHT", weight)
            .apply()
    }

    private fun loadData(){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val height = pref.getInt("KEY_HEIGHT", 0)
        val weight = pref.getInt("KEY_WEIGHT", 0)

        if(height != 0 && weight != 0){
            binding.heightEditText.setText(height.toString())
            binding.weightEditText.setText(weight.toString())
        }
    }
}