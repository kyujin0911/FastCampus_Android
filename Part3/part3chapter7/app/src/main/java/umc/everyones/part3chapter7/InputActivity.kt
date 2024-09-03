package umc.everyones.part3chapter7

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import umc.everyones.part3chapter7.databinding.ActivitiyInputBinding

class InputActivity: AppCompatActivity() {
    private lateinit var binding: ActivitiyInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitiyInputBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}