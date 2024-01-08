package umc.mission.part3chapter2

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import umc.mission.part3chapter2.databinding.ActivityVerifyOtpBinding

class VerifyOtpActivity: AppCompatActivity() {
    private lateinit var binding: ActivityVerifyOtpBinding
    private var timer: CountDownTimer? = object : CountDownTimer((3* 60 * 1000), 1000){
        override fun onTick(p0: Long) {
            TODO("Not yet implemented")
        }

        override fun onFinish() {
            TODO("Not yet implemented")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view = this
    }
}