package umc.mission.part3chapter2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import umc.mission.part3chapter2.databinding.ActivityVerifyOtpBinding

class VerifyOtpActivity: AppCompatActivity() {
    private lateinit var binding: ActivityVerifyOtpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view = this
    }
}