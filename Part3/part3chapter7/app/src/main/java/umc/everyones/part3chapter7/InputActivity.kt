package umc.everyones.part3chapter7

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import umc.everyones.part3chapter7.databinding.ActivitiyInputBinding

@AndroidEntryPoint
class InputActivity: AppCompatActivity() {
    private lateinit var binding: ActivitiyInputBinding
    private val viewModel: InputViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitiyInputBinding.inflate(layoutInflater).apply {
            setContentView(root)
            lifecycleOwner = this@InputActivity
            viewModel = this@InputActivity.viewModel
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.doneEvent.observe(this){
            Toast.makeText(this, "완료!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}